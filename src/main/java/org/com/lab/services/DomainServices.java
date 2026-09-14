package org.com.lab.services;

import org.com.lab.dto.request.CreateDomainRequest;
import org.com.lab.dto.response.DomainResponse;
import org.com.lab.entity.Domain;
import org.com.lab.entity.enums.DomainStatus;
import org.com.lab.repository.DomainJpaRepository;
import org.example.javaframework.infra.security.CurrentUserContext;
import org.example.javaframework.web.common.EnumConverter;
import org.springframework.stereotype.Service;

@Service
public class DomainServices {

    private final DomainJpaRepository domainJpaRepository;

    public DomainServices(DomainJpaRepository domainJpaRepository) {
        this.domainJpaRepository = domainJpaRepository;
    }

    public DomainResponse createDomain(CreateDomainRequest request) {
        String userId = CurrentUserContext.get().userId();
        Domain domain = new Domain();
        domain.setCreatedBy(userId);
        domain.setCode(request.code());
        domain.setName(request.name());
        domain.setStatus(EnumConverter.fromString(DomainStatus.class,request.status()));
        domainJpaRepository.save(domain);
        return DomainResponse.from(domain);
    }
}
