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


    @Transient
    @Value("${verificationToken.expirationInMinutes}")
    private static int EXPIRATION_IN_MINUTES;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String token;

    @OneToOne(targetEntity = AppUser.class,
              fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(name = "expiry_date",
            insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public VerificationToken(Date initialDate) {

        LocalDateTime initialDateTime =
                ChronoUtils.convertToLocalDateTime(initialDate);

        LocalDateTime expiryDateTime =
                initialDateTime.plus(EXPIRATION_IN_MINUTES, ChronoUnit.MINUTES);

        this.expiryDate =
                ChronoUtils.convertToDate(expiryDateTime);
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
