package cn.baltics.customer.service.impl;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.customer.dao.entity.CustomerDO;
import cn.baltics.customer.dto.req.CustomerRegisterCommitReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterVerifyReqDTO;
import cn.baltics.customer.enums.CustomerEnum;
import cn.baltics.customer.enums.CustomerErrorEnum;
import cn.baltics.customer.repository.CustomerRepository;
import cn.baltics.customer.service.CustomerService;
import cn.baltics.springboot.starter.cache.CacheService;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.convention.exception.ServiceException;
import cn.baltics.springboot.starter.mail.core.Email;
import cn.baltics.springboot.starter.mail.core.EmailProperty;
import com.alibaba.fastjson2.util.BeanUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
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
    private static final long VERIFICATION_CODE_TIMEOUT = 10L;

    @Override
    public void registerCommit(CustomerRegisterCommitReqDTO requestParam) {
        String username = requestParam.getUsername();
        String mail = requestParam.getMail();
        if (!customerRepository.registerCheckDuplication(username, mail)) {
            throw new ClientException(CustomerErrorEnum.CUSTOMER_REGISTER_DUPLICATION);
        }
        String verificationCode = generateVerificationCode();
        requestParam.setCode(verificationCode);
        try {
            Email email = Email.builder()
                    .hostName(emailProperty.getHostName())
                    .smtpPort(emailProperty.getSmtpPort())
                    .sentDate(new Date())
                    .authenticator(emailProperty.getAuthenticator())
                    .from(emailProperty.getUsername())
                    .to(requestParam.getMail())
                    .subject("[baltics]邮箱验证码")
                    .msg("您本次注册的邮箱验证码为" + verificationCode + "，验证码有效期为10分钟。")
                    .build();
            email.send();
        } catch (EmailException e) {
            throw new ClientException(CustomerErrorEnum.SEND_VERIFICATION_CODE_EMAIL_FAIL);
        }
        Customer customer;
        cacheService.put(CustomerEnum.CUSTOMER_REGISTER_VERIFICATION_CODE.name() + requestParam.getMail(),
                requestParam,
                VERIFICATION_CODE_TIMEOUT,
                TimeUnit.MINUTES);
    }

    @Override
    public void registerVerify(CustomerRegisterVerifyReqDTO requestParam) {
        CustomerRegisterCommitReqDTO cache = cacheService.get(CustomerEnum.CUSTOMER_REGISTER_VERIFICATION_CODE.name() + requestParam.getMail(), CustomerRegisterCommitReqDTO.class);
        if (cache == null || !cache.getCode().equals(requestParam.getCode())) {
            throw new ClientException(CustomerErrorEnum.VERIFICATION_CODE_ERROR);
        }


    }

    /**
     * 生成验证码
     */
    private String generateVerificationCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
