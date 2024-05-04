package com.app.fodappspringboot.controller;

import com.app.fodappspringboot.dto.AuthenticationRequest;
import com.app.fodappspringboot.dto.AuthenticationResponse;
import com.app.fodappspringboot.dto.RegisterRequest;
import com.app.fodappspringboot.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }


    @PostMapping("/validate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
