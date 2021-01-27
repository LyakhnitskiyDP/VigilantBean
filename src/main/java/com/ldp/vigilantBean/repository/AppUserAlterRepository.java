package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;

import java.util.Optional;

public interface AppUserAlterRepository {

    Optional<AppUser> signUpUser(AppUser appUserDTO);

}
