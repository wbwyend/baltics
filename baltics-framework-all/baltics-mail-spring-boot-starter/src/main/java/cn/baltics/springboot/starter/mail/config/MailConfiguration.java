package cn.baltics.springboot.starter.mail.config;

import cn.baltics.springboot.starter.mail.core.EmailProperty;
import org.apache.commons.mail.DefaultAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@name MailConfiguration 邮箱配置类
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
@Configuration
public class MailConfiguration {
    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.hostName}")
    private String hostName;

    @Value("${spring.mail.smtpPort}")
    private String smtpPort;


    @Bean
    EmailProperty emailProperty() {
        return new EmailProperty(username, hostName, smtpPort, new DefaultAuthenticator(username, password));
    }


}
