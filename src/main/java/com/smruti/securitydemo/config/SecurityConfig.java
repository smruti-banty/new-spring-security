package com.smruti.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Authentication
    @Bean
    public UserDetailsService userDetails() {
        UserDetails user1 = User.withUsername("smruti").password("{noop}banty").roles("ADMIN").build();
        UserDetails user2 = User.withUsername("rashmi").password("{noop}little").roles("STUDENT").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    // Authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/welcome/**").permitAll()
                        .requestMatchers("/student").hasRole("ADMIN")
                        .requestMatchers("/student/{roll}").hasRole("STUDENT")
                        .anyRequest()
                        .authenticated())
                .httpBasic();
        return http.build();
    }

}

/* 
 * http.csrf().disable()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/welcome/**").permitAll()
                        .requestMatchers("/student/**").authenticated())
 */

 /*
  * For method level authorize
  * @PreAuthorize("hasRole('role')") or @PreAuthorize("hasAuthorize('ROLE_role')") 
  * @EnableMethodSecurity
  */