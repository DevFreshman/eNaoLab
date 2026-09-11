package org.com.lab.dto.response;


import org.com.lab.entity.Channel;
import org.com.lab.entity.enums.ChannelStatus;

import java.time.LocalDateTime;

public record ChannelResponse(
        Long id,
        Long domainId,
        String name,
        String platformType,
        ChannelStatus status,
        String sourceUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ChannelResponse from(Channel channel) {
        return new ChannelResponse(
                channel.getId(),
                channel.getDomainId(),
                channel.getName(),
                channel.getPlatformType(),
                channel.getStatus(),
                channel.getSourceUrl(),
                channel.getCreatedAt(),
                channel.getUpdatedAt()
        );
    }
}
