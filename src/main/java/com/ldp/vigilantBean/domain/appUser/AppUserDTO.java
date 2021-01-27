package com.ldp.vigilantBean.domain.appUser;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

public class AppUserDTO implements Serializable {

    @Size(
            min = 4,
            max = 40,
            message = "validation.newUser.username.tooShort"
    )
    private String username;

    @Pattern(
            regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,5}$",
            message = "validation.newUser.email.isNotValid"
    )
    private String email;

    @Pattern(
            regexp = ".*\\d",
            message = "validation.newUser.password.noDigits"
    )
    @Pattern(
            regexp = "(?=.*[A-Z]).+",
            message = "validation.newUser.password.noUpperCaseLetters"
    )
    @Size(
            min = 8,
            message = "validation.newUser.password.tooShort"
    )
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserDTO that = (AppUserDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "AppUserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
