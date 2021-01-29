package com.ldp.vigilantBean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Component
@PropertySource("classpath:/mailSender.properties")
public class MailSessionFactory {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private String port;

    @Value("${companyEmail}")
    private String companyEmail;

    @Value("${password}")
    private String password;

    @Value("${mail.transport.protocol}")
    private String mailProtocol;

    @Value("${mail.smtp.auth}")
    private boolean smtpAuth;

    @Value("${mail.smtp.starttls.enable}")
    private String starttlsEnabled;

    @Value("${mail.debug}")
    private boolean debugMode;

    @Value("${mail.smtp.ssl.trust}")
    private String sslTrust;

    @Bean(name = "mailSession")
    public Session getSession() {

        Properties prop = new Properties();
        initMailProperties(prop);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(companyEmail, password);
            }
        };

        return Session.getInstance(prop, authenticator);
    }

    private void initMailProperties(Properties mailProps) {

        mailProps.put("mail.smtp.auth", smtpAuth);
        mailProps.put("mail.smtp.starttls.enable", starttlsEnabled);
        mailProps.put("mail.smtp.host", host);
        mailProps.put("mail.smtp.port", port);
        mailProps.put("mail.smtp.ssl.trust", sslTrust);
    }

}
