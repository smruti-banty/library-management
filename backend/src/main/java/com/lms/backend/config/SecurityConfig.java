package com.lms.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lms.backend.constants.UserRole;
import com.lms.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;

    @Bean
    UserDetailsService userDetails() {
        return (username) -> {
            return userRepository.findByReferenceNumber(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/swagger-ui.html","/swagger-ui/**", "/v1/library-management-api-docs/**").permitAll()
                        .requestMatchers("/api/v1/user/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/**").hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasAuthority(UserRole.ADMIN.name())
                        .anyRequest()
                        .authenticated())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
