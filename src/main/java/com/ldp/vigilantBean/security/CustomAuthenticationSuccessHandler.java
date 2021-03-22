package com.ldp.vigilantBean.security;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private AppUserRetrievalService appUserRetrievalService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public CustomAuthenticationSuccessHandler(
            @Autowired
            AppUserRetrievalService appUserRetrievalService) {

        this.appUserRetrievalService = appUserRetrievalService;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException, ServletException {

        Optional<AppUser> optAppUser =
                appUserRetrievalService.getAppUserByEmail(authentication.getName());

        if (!optAppUser.isPresent())
            throw new AuthenticationCredentialsNotFoundException("Authenticated user is not found");
        AppUserDetails appUserDetails = new AppUserDetails(optAppUser.get());

        Authentication userAuthentication =
                new UsernamePasswordAuthenticationToken(
                        appUserDetails,
                        "[PROTECTED]",
                        authentication.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(
                userAuthentication
        );

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/account");
    }
}

