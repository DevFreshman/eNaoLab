package org.com.lab.entity.enums;

import org.com.lab.error.LabErrorCode;
import org.example.javaframework.web.exception.BusinessException;

public enum ProcessingStatus {
    NEW,
    PROCESSED,
    PENDING,
    ERROR;
    public static ProcessingStatus fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new BusinessException(LabErrorCode.INVALID_INPUT, value);
        }
        try {
            return ProcessingStatus.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException(LabErrorCode.INVALID_INPUT, value);
        }
    }
}
