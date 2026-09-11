package org.com.lab.dto.request;

import java.time.LocalDateTime;

public record CreateRecordRequest(
        Long channelId,
        String title,
        String content,
        LocalDateTime publishAt,
        LocalDateTime crawledAt,
        String processingStatus,
        String errorMessage
        ) {
}
