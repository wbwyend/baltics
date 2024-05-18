package cn.baltics.springboot.starter.mail;

import cn.baltics.springboot.starter.designpattern.builder.Builder;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Date;

/**
 *@name Email 电子邮件
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
public class Email extends HtmlEmail implements Builder<Email> {

    public Email hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public Email smtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
        return this;
    }

    public Email authenticator(DefaultAuthenticator authenticator) {
        this.authenticator = authenticator;
        return this;
    }

    public Email from(String email) throws EmailException {
        super.setFrom(email);
        return this;
    }

    public Email sentDate(Date sentDate) {
        this.sentDate = new Date(sentDate.getTime());
        return this;
    }

    public Email subject(String subject) {
        super.setSubject(subject);
        return this;
    }

    public Email msg(String msg) throws EmailException {
        super.setMsg(msg);
        return this;
    }

    public Email to(String email) throws EmailException {
        super.addTo(email);
        return this;
    }

    @Override
    public String send() throws EmailException {
        return super.send();
    }

    private Email() {}

    public static Email builder() {
        return new Email();
    }

    @Override
    public Email build() {
        super.setSSLOnConnect(true);
        return this;
    }
}
