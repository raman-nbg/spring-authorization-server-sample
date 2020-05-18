package com.github.ramannbg.springauthorizationserversample.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // WARN: Do not use the default password encoder in production environments!
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                .username("user-a")
                .password("password")
                .roles("USER_ROLE")
                .build()
        );
    }
}
