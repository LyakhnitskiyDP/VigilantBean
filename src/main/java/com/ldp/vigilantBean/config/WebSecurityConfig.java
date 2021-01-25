package com.ldp.vigilantBean.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authenticationProvider;

    public WebSecurityConfig(
            @Autowired
            AuthenticationProvider authenticationProvider) {

    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                    .antMatchers("/signUp/**")
                    .permitAll();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authenticationProvider);
    }

    @Bean(name = "sCryptPasswordEncoder")
    public PasswordEncoder sCryptPasswordEncoder() {

        return new SCryptPasswordEncoder();
    }


}
