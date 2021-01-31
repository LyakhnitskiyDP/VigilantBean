package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.repository.AppUserAlterRepository;
import com.ldp.vigilantBean.service.AppUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserRegistrationServiceImpl implements AppUserRegistrationService {

    private AppUserAlterRepository appUserAlterRepository;
    private PasswordEncoder passwordEncoder;

    public AppUserRegistrationServiceImpl(
            @Autowired
            AppUserAlterRepository appUserAlterRepository,
            @Autowired
            PasswordEncoder passwordEncoder) {

       this.appUserAlterRepository = appUserAlterRepository;
       this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> registerUser(AppUserDTO appUserDTO) {

        AppUser user = new AppUser();

        user.setEmail(appUserDTO.getEmail());
        user.setUsername(appUserDTO.getUsername());
        user.setPassword(
                passwordEncoder.encode(appUserDTO.getPassword())
        );

        return appUserAlterRepository.registerUser(user);
    }

    @Override
    public boolean enableUser(AppUser appUser) {

        return appUserAlterRepository.enableUser(appUser);
    }
}
