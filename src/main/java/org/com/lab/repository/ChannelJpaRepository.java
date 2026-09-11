package org.com.lab.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.lab.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChannelJpaRepository extends JpaRepository<Channel, Long>, JpaSpecificationExecutor<Channel> {

    @Query("""
        SELECT c
        FROM Channel c
        JOIN UserDomain ud
            ON ud.domainId = c.domainId
        WHERE c.id = :channelId
          AND ud.userId = :userId
          AND ud.revokedAt IS NULL
    """)
    Optional<Channel> findAccessibleChannel(
            @Param("channelId") Long channelId,
            @Param("userId") String userId
    );

    List<Channel> findByDomainId(Long domainId);

}
