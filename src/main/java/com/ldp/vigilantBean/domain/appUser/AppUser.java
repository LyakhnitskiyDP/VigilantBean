package com.ldp.vigilantBean.domain.appUser;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "app_user")
@NamedQueries({
        @NamedQuery(name = AppUser.GET_APP_USER_BY_EMAIL,
                    query = "from AppUser appUser " +
                            "where appUser.email = :email")
})
public class AppUser {

    public static final String GET_APP_USER_BY_EMAIL =
            "AppUser.getAppUserByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id")
    private Long appUserId;

    @ManyToMany
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "app_user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "not_locked")
    private boolean notLocked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(appUserId, appUser.appUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId);
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isNotLocked() {
        return notLocked;
    }

    public void setNotLocked(boolean notLocked) {
        this.notLocked = notLocked;
    }
}
