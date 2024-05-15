package com.app.fodappspringboot.dto;


public record RegisterRequest(
        String password,
        String firstName,
        String lastName,
        String emailAddress
) {
}
