package com.app.fodappspringboot.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
