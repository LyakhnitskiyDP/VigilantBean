package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.appUser.AppUser;

import java.util.Optional;

public interface AppUserRetrievalRepository {

    Optional<AppUser> getAppUserByEmail(String email);

}
