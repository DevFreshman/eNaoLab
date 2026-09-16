package org.com.lab.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateRecordRequest(
        @NotNull @Positive Long channelId,
        @NotBlank String title,
        @NotBlank String content,
        @NotNull LocalDateTime publishAt,
        @NotNull LocalDateTime crawledAt,
        @NotBlank String processingStatus,
        String errorMessage // Cho phép null khi không có lỗi
) {
}