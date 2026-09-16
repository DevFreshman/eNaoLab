package org.com.lab.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.com.lab.entity.enums.ChannelStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "LAB_CHANNEL")
@Getter
@Setter
@NoArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(
            name = "DOMAIN_ID",
            nullable = false
    )
    private Long domainId;

    @Column(
            name = "NAME",
            nullable = false,
            length = 255
    )
    private String name;

    @Column(
            name = "PLATFORM_TYPE",
            nullable = false,
            length = 50
    )
    private String platformType;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "STATUS",
            nullable = false,
            length = 20
    )
    private ChannelStatus status;

    @Column(
            name = "SOURCE_URL",
            length = 1000
    )
    private String sourceUrl;

    @Lob
    @Column(name = "CONFIG")
    private String config;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(
            name = "CREATED_AT",
            nullable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "CREATED_BY",
            nullable = false
    )
    private String createdBy;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
}