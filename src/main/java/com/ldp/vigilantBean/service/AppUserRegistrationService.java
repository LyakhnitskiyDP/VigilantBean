package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;

import java.util.Optional;

public interface AppUserRegistrationService {

    Optional<AppUser> registerUser(AppUserDTO appUserDTO);

}
