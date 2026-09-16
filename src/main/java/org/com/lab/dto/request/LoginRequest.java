package org.com.lab.dto.request;

public record LoginRequest(
        String username,
        String password
) {
}
