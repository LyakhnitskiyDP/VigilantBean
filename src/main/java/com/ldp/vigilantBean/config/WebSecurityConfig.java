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

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authenticationProvider;

    public WebSecurityConfig(
            @Autowired
            AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/shop/sweets")
                    .and()
                    .logout()
                    .permitAll();

        httpSecurity.authorizeRequests()
                    .antMatchers("/home/**").permitAll()
                    .antMatchers("/shop/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/account/**").hasRole("CUSTOMER")
                    .antMatchers("/cart/**").hasRole("CUSTOMER");

        httpSecurity.csrf()
                    .disable();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authenticationProvider);
    }

}
