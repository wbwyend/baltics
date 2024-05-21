package cn.baltics.customer.service.handler;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.customer.dto.req.CustomerLoginReqDTO;
import cn.baltics.customer.dto.resp.CustomerLoginRespDTO;
import cn.baltics.customer.enums.CustomerEnum;
import cn.baltics.customer.enums.CustomerErrorEnum;
import cn.baltics.customer.enums.CustomerLoginMethodEnum;
import cn.baltics.customer.repository.CustomerRepository;
import cn.baltics.springboot.starter.cache.CacheService;
import cn.baltics.springboot.starter.common.util.StringUtil;
import cn.baltics.springboot.starter.common.util.TokenUtil;
import cn.baltics.springboot.starter.common.util.VerificationCodeUtil;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategyExecutor;
import cn.baltics.springboot.starter.mail.core.Email;
import cn.baltics.springboot.starter.mail.core.EmailProperty;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *@func 邮箱登录策略执行器
 *
 *@author wbwyend
 *@date 2024/05/20 
 */
@Component
@AllArgsConstructor
public class MailLoginExecutor implements AbstractStrategyExecutor<CustomerLoginReqDTO, CustomerLoginRespDTO> {
    private static final long VERIFICATION_CODE_TIMEOUT = 10;
    private EmailProperty emailProperty;
    private CacheService cacheService;
    private CustomerRepository customerRepository;

    @Override
    public CustomerLoginRespDTO executeResp(CustomerLoginReqDTO requestParam) {
        Customer customer = customerRepository.getCustomerByUsername(requestParam.getUsername());
        if (customer == null || StringUtil.isNullOrBlank(customer.getMail())) {
            throw new ClientException(CustomerErrorEnum.USERNAME_OR_MAIL_ERROR);
        }
        if (StringUtil.isNullOrBlank(requestParam.getVerificationCode())) {
            sendVerificationCode(customer);
            return null;
        } else {
            checkVerificationCode(requestParam);
            return CustomerLoginRespDTO.builder()
                    .token(TokenUtil.createToken(customer.getId().toString(), customer.getPassword()))
                    .build();
        }
    }

    private void checkVerificationCode(CustomerLoginReqDTO requestParam) {
        String verificationCode = cacheService.get(CustomerEnum.CUSTOMER_LOGIN_VERIFICATION_CODE.name() + requestParam.getUsername(), String.class);
        if (!requestParam.getVerificationCode().equals(verificationCode)) {
            throw new ClientException(CustomerErrorEnum.VERIFICATION_CODE_ERROR);
        }
    }

    private void sendVerificationCode(Customer customer) {
        String verificationCode = VerificationCodeUtil.getNewCode();
        try {
            Email email = Email.builder()
                    .hostName(emailProperty.getHostName())
                    .smtpPort(emailProperty.getSmtpPort())
                    .sentDate(new Date())
                    .authenticator(emailProperty.getAuthenticator())
                    .from(emailProperty.getUsername())
                    .to(customer.getMail())
                    .subject("[baltics]邮箱验证码")
                    .msg("您本次登录的邮箱验证码为" + verificationCode + "，验证码有效期为" + VERIFICATION_CODE_TIMEOUT + "分钟。")
                    .build();
            email.send();
        } catch (EmailException e) {
            throw new ClientException(CustomerErrorEnum.SEND_VERIFICATION_CODE_EMAIL_FAIL);
        }
        cacheService.put(CustomerEnum.CUSTOMER_LOGIN_VERIFICATION_CODE.name() + customer.getUsername(),
                verificationCode,
                VERIFICATION_CODE_TIMEOUT,
                TimeUnit.MINUTES);
    }

    @Override
    public String mask() {
        return CustomerLoginMethodEnum.MAIL.name();
    }
}
