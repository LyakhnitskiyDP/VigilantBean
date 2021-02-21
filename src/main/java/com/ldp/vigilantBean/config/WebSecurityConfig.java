package com.ldp.vigilantBean.config;


import com.ldp.vigilantBean.security.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authenticationProvider;

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(
            @Autowired
            AuthenticationProvider authenticationProvider,
            @Autowired
            UserDetailsService userDetailsService) {

        this.authenticationProvider = authenticationProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/account")
                    .and()
                        .rememberMe()
                        .key("rem-me-key")
                        .userDetailsService(userDetailsService)
                        .rememberMeParameter("remember")
                        .rememberMeCookieName("rememberMeCookie")
                        .tokenValiditySeconds(60 * 60 * 24 * 3)
                    .and()
                        .logout()
                        .deleteCookies("JSESSIONID", "rememberMeCookie")
                        .permitAll();

        httpSecurity.authorizeRequests()
                    .mvcMatchers("/admin").hasRole("ADMIN")
                    .mvcMatchers("/customer").hasRole("CUSTOMER")
                    .mvcMatchers("/cart").hasRole("CUSTOMER")
                    .anyRequest().permitAll();

        httpSecurity.csrf().disable();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authenticationProvider);
    }

}
