package cn.baltics.customer.service.impl;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.customer.dto.req.CustomerLoginReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterCommitReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterVerifyReqDTO;
import cn.baltics.customer.dto.resp.CustomerLoginRespDTO;
import cn.baltics.customer.enums.CustomerEnum;
import cn.baltics.customer.enums.CustomerErrorEnum;
import cn.baltics.customer.repository.CustomerRepository;
import cn.baltics.customer.service.CustomerService;
import cn.baltics.springboot.starter.cache.CacheService;
import cn.baltics.springboot.starter.common.util.TokenUtil;
import cn.baltics.springboot.starter.common.util.VerificationCodeUtil;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategySelector;
import cn.baltics.springboot.starter.distributedid.SnowflakeIdUtil;
import cn.baltics.springboot.starter.mail.core.Email;
import cn.baltics.springboot.starter.mail.core.EmailProperty;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *@name CustomerServiceImpl
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private EmailProperty emailProperty;
    private CacheService cacheService;
    private AbstractStrategySelector abstractStrategySelector;
    private static final long VERIFICATION_CODE_TIMEOUT = 10L;
    @Override
    public void registerCommit(CustomerRegisterCommitReqDTO requestParam) {
        String verificationCode = VerificationCodeUtil.getNewCode();
        Customer customer = Customer.builder()
                .username(requestParam.getUsername())
                .password(requestParam.getPassword())
                .mail(requestParam.getMail())
                .verificationCode(verificationCode)
                .build();
        if (!customerRepository.registerCheckDuplication(customer)) {
            throw new ClientException(CustomerErrorEnum.CUSTOMER_REGISTER_DUPLICATION);
        }
        try {
            Email email = Email.builder()
                    .hostName(emailProperty.getHostName())
                    .smtpPort(emailProperty.getSmtpPort())
                    .sentDate(new Date())
                    .authenticator(emailProperty.getAuthenticator())
                    .from(emailProperty.getUsername())
                    .to(requestParam.getMail())
                    .subject("[baltics]邮箱验证码")
                    .msg("您本次注册的邮箱验证码为" + verificationCode + "，验证码有效期为" + VERIFICATION_CODE_TIMEOUT + "分钟。")
                    .build();
            email.send();
        } catch (EmailException e) {
            throw new ClientException(CustomerErrorEnum.SEND_VERIFICATION_CODE_EMAIL_FAIL);
        }
        cacheService.put(CustomerEnum.CUSTOMER_REGISTER_VERIFICATION_CODE.name() + requestParam.getMail(),
                customer,
                VERIFICATION_CODE_TIMEOUT,
                TimeUnit.MINUTES);
    }

    @Override
    public CustomerLoginRespDTO registerVerify(CustomerRegisterVerifyReqDTO requestParam) {
        Customer customer = cacheService.get(CustomerEnum.CUSTOMER_REGISTER_VERIFICATION_CODE.name() + requestParam.getMail(), Customer.class);
        if (customer == null || !customer.getVerificationCode().equals(requestParam.getVerificationCode())) {
            throw new ClientException(CustomerErrorEnum.VERIFICATION_CODE_ERROR);
        }
        customer.setId(SnowflakeIdUtil.nextId());
        customerRepository.saveRegisterCustomer(customer);
        return CustomerLoginRespDTO.builder()
                .token(TokenUtil.createToken(customer.getId().toString(), customer.getPassword()))
                .build();
    }

    @Override
    public CustomerLoginRespDTO login(CustomerLoginReqDTO requestParam) {
        return abstractStrategySelector.chooseAndExecuteResp(requestParam.buildMask(), requestParam);
    }

}
