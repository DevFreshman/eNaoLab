package org.com.lab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.com.lab.entity.enums.ProcessingStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "LAB_CRAWLED_RECORD")
@Getter
@Setter
@NoArgsConstructor
public class CrawledRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(
            name = "CHANNEL_ID",
            nullable = false
    )
    private Long channelId;

    @Column(
            name = "TITLE",
            length = 500
    )
    private String title;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PUBLISHED_AT")
    private LocalDateTime publishedAt;

    @Column(
            name = "CRAWLED_AT",
            nullable = false
    )
    private LocalDateTime crawledAt;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "PROCESSING_STATUS",
            nullable = false,
            length = 20
    )
    private ProcessingStatus processingStatus;

    @Column(
            name = "ERROR_MESSAGE",
            length = 1000
    )
    private String errorMessage;

    @CreationTimestamp
    @Column(
            name = "CREATED_AT",
            nullable = false
    )
    private LocalDateTime createdAt;
}
