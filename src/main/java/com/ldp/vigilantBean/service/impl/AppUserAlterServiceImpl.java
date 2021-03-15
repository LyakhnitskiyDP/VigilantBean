package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.order.Cart;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.repository.AppUserAlterRepository;
import com.ldp.vigilantBean.service.AppUserAlterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class AppUserAlterServiceImpl implements AppUserAlterService {

    private AppUserAlterRepository appUserAlterRepository;
    private PasswordEncoder passwordEncoder;

    public AppUserAlterServiceImpl(
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
        user.setCart(new Cart());
        user.setPassword(
                passwordEncoder.encode(appUserDTO.getPassword())
        );

        return appUserAlterRepository.registerUser(user);
    }

    @Override
    public boolean enableUser(AppUser appUser) {

        return appUserAlterRepository.enableUser(appUser);
    }

    @Override
    public Optional<AppUser> updateUser(AppUser appUser) {

        return appUserAlterRepository.updateUser(appUser);
    }
}
