package org.com.lab.dto.request;

public record CreateDomainRequest(
        String code,
        String name,
        String status
) {
}
