package com.eod.sitree.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    protected ApplicationErrorType errorType;
    protected HttpStatus httpStatus;

    public CustomException(ApplicationErrorType errorType) {
        this.errorType = errorType;
        this.httpStatus = HttpStatus.OK;
    }

    public CustomException(ApplicationErrorType errorType, HttpStatus httpStatus) {
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return this.errorType.getMessage();
    }
}
