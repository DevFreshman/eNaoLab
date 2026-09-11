package org.com.lab.repository;

import org.com.lab.entity.Domain;
import org.com.lab.entity.enums.DomainStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainJpaRepository extends JpaRepository<Domain, Long> {
    List<Domain> findByStatus(DomainStatus status);
}
