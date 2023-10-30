package com.lms.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfig {

    @Bean
    UserDetailsService userDetails() {
        UserDetails user1 = User.withUsername("smruti").password("{noop}banty").roles("ADMIN").build();
        UserDetails user2 = User.withUsername("rashmi").password("{noop}little").roles("USER").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/swagger-ui/**", "/v1/library-management-api-docs/**").permitAll()
                        .requestMatchers("/api/v1/user/create").permitAll()
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
