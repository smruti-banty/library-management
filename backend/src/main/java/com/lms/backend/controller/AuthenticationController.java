package com.lms.backend.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.LoginRequestDto;
import com.lms.backend.dto.LoginResponseDto;
import com.lms.backend.services.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        var usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authenticate = authenticationManager.authenticate(usernamePasswordAuthentication);

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        var token = tokenService.generateToken(authenticate);

        return new LoginResponseDto(token);
    }
}
