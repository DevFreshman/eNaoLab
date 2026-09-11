package org.com.lab.error;

import org.example.javaframework.web.common.InterfaceErrorCode;
import org.springframework.http.HttpStatus;

public enum LabErrorCode implements InterfaceErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND),
    CHANNEL_NOT_FOUND(HttpStatus.NOT_FOUND),
    CRAWLED_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND),
    DOMAIN_ACCESS_DENIED(HttpStatus.FORBIDDEN),
    INVALID_INPUT(HttpStatus.BAD_REQUEST),
    INVALID_LIMIT(HttpStatus.BAD_REQUEST);

    private final HttpStatus httpStatus;

    LabErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
