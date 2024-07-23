package com.eod.sitree.auth.exception;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AuthException extends CustomException {

    public AuthException(ApplicationErrorType errorType) {
        super(errorType);
    }

    public AuthException(ApplicationErrorType errorType,
        HttpStatus httpStatus) {
        super(errorType, httpStatus);
    }
}
