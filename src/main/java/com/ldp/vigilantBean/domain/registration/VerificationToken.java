package com.ldp.vigilantBean.domain.registration;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.utils.ChronoUtils;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@Entity
@PropertySource("classpath:webappConfig.properties")
public class VerificationToken implements Serializable {

    @Value("${verificationToken.expirationInMinutes}")
    private static int EXPIRATION_IN_MINUTES;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = AppUser.class,
              fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public VerificationToken(Date initialDate) {

        LocalDateTime initialDateTime = ChronoUtils.

        LocalDateTime expiryDateTime =
                initialDateTime.plus(EXPIRATION_IN_MINUTES, ChronoUnit.MINUTES);

        this.expiryDate =
                Date.from(expiryDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }




}
