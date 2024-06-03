package com.app.fodappspringboot.dto;

public record UpdateUserRequest(
        String firstName,
        String lastName,
        String emailAddress
) {
}
