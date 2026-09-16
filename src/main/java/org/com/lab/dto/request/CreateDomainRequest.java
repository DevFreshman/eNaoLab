package org.com.lab.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDomainRequest(
        @NotBlank String code,
        @NotNull String name,
        @NotBlank String status
) {
}
