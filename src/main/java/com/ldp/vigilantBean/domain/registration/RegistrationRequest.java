package com.ldp.vigilantBean.domain.registration;

public class RegistrationRequest {

    private final String email;

    private final String password;

    private final String username;

    public RegistrationRequest(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
