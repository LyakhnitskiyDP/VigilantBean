package com.ldp.vigilantBean.domain.registration;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationEvent extends ApplicationEvent {

    private String appUrl;

    private Locale locale;

    private AppUser user;

    public OnRegistrationEvent(AppUser source,
                               Locale locale,
                               String appUrl) {
        super(source);

        this.appUrl = appUrl;
        this.locale = locale;
        this.user = source;
    }


}
