package com.ldp.vigilantBean.domain.registration;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.service.VerificationTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


import java.util.Locale;
import java.util.UUID;

@Component
public class OnRegistrationListener
        implements ApplicationListener<OnRegistrationEvent> {

    private static final Logger log =
            LogManager.getLogger(OnRegistrationListener.class.getName());

    private VerificationTokenService verificationTokenService;

    private MessageSource messageSource;

    private JavaMailSender mailSender;

    private AppUser user;

    private Locale locale;

    private String appUrl;

    private String token;

    public OnRegistrationListener(
            @Autowired
            VerificationTokenService verificationTokenService,
            @Autowired
            MessageSource messageSource,
            @Autowired
            JavaMailSender mailSender) {

        this.verificationTokenService = verificationTokenService;
        this.messageSource = messageSource;
        this.mailSender = mailSender;
    }


    @Override
    public void onApplicationEvent(OnRegistrationEvent onRegistrationEvent) {

        extractData(onRegistrationEvent);

        initToken();

        sendMessage();
    }

    private void extractData(OnRegistrationEvent event) {
        this.user = event.getUser();
        this.locale = event.getLocale();
        this.appUrl = event.getAppUrl();
    }

    private void initToken() {
        this.token = UUID.randomUUID().toString();
    }

    private void sendMessage() {

        String recipientAddress = user.getEmail();
        String subject =
                messageSource.getMessage("event.registrationConfirm.subject", null, locale);

        String confirmationURL =
                appUrl + "/registrationConfirm?token=" + token;

        String confirmationMessage =
                messageSource.getMessage("event.registrationConfirm.message", null, locale);


        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(recipientAddress);
        mail.setSubject(subject);
        mail.setText(confirmationMessage + "\r\n" + "http://localhost:8080" + confirmationURL);

        mailSender.send(mail);

        log.info("Message has been sent");
    }
}
