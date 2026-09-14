package org.com.lab.entity.enums;

import org.com.lab.error.LabErrorCode;
import org.example.javaframework.web.exception.BusinessException;

public enum ProcessingStatus {
    NEW,
    PROCESSED,
    PENDING,
    ERROR;
}
