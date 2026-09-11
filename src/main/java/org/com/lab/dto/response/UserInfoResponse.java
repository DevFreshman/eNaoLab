package org.com.lab.dto.response;

import lombok.Builder;

@Builder
public record UserInfoResponse(
        String username,
        String email,
        String fullName,
        String role
) {
}
