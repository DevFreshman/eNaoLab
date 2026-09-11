package org.com.lab.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "LAB_USER_DOMAIN")
@Getter
@Setter
@NoArgsConstructor
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(
            name = "USER_ID",
            nullable = false
    )
    private String userId;

    @Column(
            name = "DOMAIN_ID",
            nullable = false
    )
    private Long domainId;

    @Column(
            name = "ASSIGNED_AT",
            nullable = false
    )
    private LocalDateTime assignedAt;

    @Column(
            name = "ASSIGNED_BY",
            nullable = false
    )
    private String assignedBy;

    @Column(name = "REVOKED_AT")
    private LocalDateTime revokedAt;

    @Column(name = "REVOKED_BY")
    private String revokedBy;
}
