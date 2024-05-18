package cn.baltics.springboot.starter.mail.config;

import org.apache.commons.mail.DefaultAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@name AuthenticationConfiguration 邮箱配置类
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
@Configuration
public class AuthenticationConfiguration {
    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    DefaultAuthenticator defaultAuthenticator() {
        return new DefaultAuthenticator(username, password);
    }
}
