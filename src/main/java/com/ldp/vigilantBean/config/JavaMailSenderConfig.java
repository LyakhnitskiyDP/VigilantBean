package com.ldp.vigilantBean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:/mailSender.properties")
public class JavaMailSenderConfig {

    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;

    @Value("${useranme}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${mail.transport.protocol")
    private String mailProtocol;

    @Value("${mail.smtp.auth}")
    private boolean smtpAuth;

    @Value("${mail.smtp.starttls.enable}")
    private boolean starttlsEnabled;

    @Value("${mail.debug}")
    private boolean debugMode;

    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        initMailSender(mailSender);

        Properties mailProps = mailSender.getJavaMailProperties();
        initMailProperties(mailProps);

        return mailSender;
    }

    private void initMailSender(JavaMailSenderImpl mailSender) {

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
    }

    private void initMailProperties(Properties mailProps) {

        mailProps.put("mail.transport.protocol", mailProtocol);
        mailProps.put("mail.smtp.auth", smtpAuth);
        mailProps.put("mail.smtp.starttls.enable", starttlsEnabled);
        mailProps.put("mail.debug", debugMode);
    }

}
