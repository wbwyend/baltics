package cn.baltics.springboot.starter.mail.core;

import org.apache.commons.mail.DefaultAuthenticator;

/**
 *@name EmailProperty
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public class EmailProperty {
    private String username;
    private String hostName;
    private String smtpPort;
    private DefaultAuthenticator authenticator;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public DefaultAuthenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(DefaultAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    public EmailProperty(String username, String hostName, String smtpPort, DefaultAuthenticator authenticator) {
        this.username = username;
        this.hostName = hostName;
        this.smtpPort = smtpPort;
        this.authenticator = authenticator;
    }
}
