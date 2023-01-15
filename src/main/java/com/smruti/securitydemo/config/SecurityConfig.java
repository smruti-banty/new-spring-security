package com.smruti.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Authentication
    @Bean
    public UserDetailsService userDetails() {
        UserDetails user1 = User.withUsername("smruti").password("{noop}banty").roles("ADMIN").build();
        UserDetails user2 = User.withUsername("rashmi").password("{noop}little").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
