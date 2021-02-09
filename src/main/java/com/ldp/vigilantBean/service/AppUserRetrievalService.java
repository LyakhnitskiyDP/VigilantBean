package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.appUser.AppUser;

import java.util.Optional;

public interface AppUserRetrievalService {

    Optional<AppUser> getAppUserByEmail(String email);
}
