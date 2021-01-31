package com.ldp.vigilantBean.domain.registration;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.utils.ChronoUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "verification_token")
@NamedQueries({
        @NamedQuery(
                name = VerificationToken.GET_BY_TOKEN,
                query = "from VerificationToken t " +
                        "where t.token = :token"
        )
})
public class VerificationToken implements Serializable {

    public static final String GET_BY_TOKEN =
            "VerificationToken.getByToken";

    @Transient
    private int expirationInMinutes;

    @Id
    @Column(name = "verification_token_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verificationTokenId;

    @Column
    private String token;

    @OneToOne(targetEntity = AppUser.class,
              fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public VerificationToken(Date initialDate, int expirationInMinutes) {

        this.expirationInMinutes = expirationInMinutes;

        LocalDateTime initialDateTime =
                ChronoUtil.convertToLocalDateTime(initialDate);

        LocalDateTime expiryDateTime =
                initialDateTime.plus(expirationInMinutes, ChronoUnit.MINUTES);

        this.expiryDate =
                ChronoUtil.convertToDate(expiryDateTime);
    }

    public boolean isExpired(Date dateToCompare) {

        return expiryDate.before(dateToCompare);
    }

    public VerificationToken() {
        this(new Date(), 1440);
    }

    public Long getVerificationTokenId() {
        return verificationTokenId;
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

    public int getExpirationInMinutes() {
        return expirationInMinutes;
    }

    public void setExpirationInMinutes(int expirationInMinutes) {
        this.expirationInMinutes = expirationInMinutes;
    }
}
