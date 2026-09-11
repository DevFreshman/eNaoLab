package org.com.lab.dto.response;

import org.com.lab.entity.Domain;
import org.com.lab.entity.enums.DomainStatus;

import java.time.LocalDateTime;

public record DomainResponse(
        Long id,
        String code,
        String name,
        DomainStatus status,
        LocalDateTime createdAt,
        String createdBy
) {
    public static DomainResponse from(Domain domain) {
        return new DomainResponse(
                domain.getId(),
                domain.getCode(),
                domain.getName(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getCreatedBy()
        );
    }
}
