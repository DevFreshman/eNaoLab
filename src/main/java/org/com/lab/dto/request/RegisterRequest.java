package org.com.lab.dto.request;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String role
) {
}
