package org.com.lab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.com.lab.entity.enums.DomainStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "LAB_DOMAIN")
@Getter
@Setter
@NoArgsConstructor
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(
            name = "CODE",
            nullable = false,
            unique = true,
            length = 50
    )
    private String code;

    @Column(
            name = "NAME",
            nullable = false,
            length = 255
    )
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "STATUS",
            nullable = false,
            length = 20
    )
    private DomainStatus status;


    @CreationTimestamp
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
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
}