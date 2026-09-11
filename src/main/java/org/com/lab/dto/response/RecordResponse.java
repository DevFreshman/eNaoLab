package org.com.lab.dto.response;

import org.com.lab.entity.CrawledRecord;

import java.time.LocalDateTime;

public record RecordResponse(
        Long channelId,
        String title,
        String content,
        LocalDateTime publishAt,
        LocalDateTime crawledAt,
        String processingStatus,
        String errorMessage,
        LocalDateTime createdAt
) {
    public static RecordResponse from(CrawledRecord record) {
        return new RecordResponse(
                record.getChannelId(),
                record.getTitle(),
                record.getContent(),
                record.getPublishedAt(),
                record.getCrawledAt(),
                record.getProcessingStatus().name(),
                record.getErrorMessage(),
                record.getCreatedAt()
        );
    }
}
