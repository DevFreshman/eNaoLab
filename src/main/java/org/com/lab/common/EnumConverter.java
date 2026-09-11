package org.com.lab.common;

import org.com.lab.error.LabErrorCode;
import org.example.javaframework.web.exception.BusinessException;

public class EnumConverter {

    private EnumConverter() {
    }

    public static <T extends Enum<T>> T fromString(Class<T> enumClass, String value) {
        if (value == null || value.isBlank()) {
            throw new BusinessException(LabErrorCode.INVALID_INPUT, value);
        }
        try {
            return Enum.valueOf(enumClass, value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException(LabErrorCode.INVALID_INPUT, value);
        }
    }
}
