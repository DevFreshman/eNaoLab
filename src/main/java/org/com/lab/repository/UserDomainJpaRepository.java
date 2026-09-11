package org.com.lab.repository;

import org.com.lab.entity.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDomainJpaRepository extends JpaRepository<UserDomain, Integer> {
    List<UserDomain> findByUserIdAndRevokedAtNull(String userId);
}
