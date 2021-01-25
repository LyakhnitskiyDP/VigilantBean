package com.ldp.vigilantBean.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StandardPasswordEncoder implements PasswordEncoder {

    private PasswordEncoder passwordEncoder =
            new SCryptPasswordEncoder();

    @Override
    public String encode(CharSequence charSequence) {
        return passwordEncoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return passwordEncoder.matches(charSequence, s);
    }
}
