package com.ldp.vigilantBean.domain.registration;

import com.ldp.vigilantBean.config.MailSessionFactory;
import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.service.VerificationTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Component
public class OnRegistrationListener
        implements ApplicationListener<OnRegistrationEvent> {

    private static final Logger log =
            LogManager.getLogger(OnRegistrationListener.class.getName());

    private VerificationTokenService verificationTokenService;

    private MessageSource messageSource;

    private MailSessionFactory mailSessionFactory;

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
            MailSessionFactory mailSessionFactory) {

        this.verificationTokenService = verificationTokenService;
        this.messageSource = messageSource;
        this.mailSessionFactory = mailSessionFactory;
    }

    @Override
    public void onApplicationEvent(OnRegistrationEvent onRegistrationEvent) {

        extractData(onRegistrationEvent);

        initToken();

        persistVerificationToken();

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

    private void persistVerificationToken() {

        verificationTokenService.create(user, token);
    }

    private void sendMessage() {

        String recipientAddress = user.getEmail();
        String subject =
                messageSource.getMessage("event.registrationConfirm.subject", null, locale);

        String confirmationURL =
                appUrl + "/signUp/registrationConfirm?token=" + token;

        String confirmationMessage =
                messageSource.getMessage("event.registrationConfirm.message", null, locale);

        Message message = new MimeMessage(
                mailSessionFactory.getSession()
        );

        try {
            message.setFrom(new InternetAddress("vigilantbean@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(confirmationMessage + "\r\n" + "http://localhost:8080" + confirmationURL, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


        log.info("Message has been sent" + this);
    }
}
