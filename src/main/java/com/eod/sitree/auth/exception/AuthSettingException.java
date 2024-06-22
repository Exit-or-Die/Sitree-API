package com.eod.sitree.auth.exception;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AuthSettingException extends CustomException {

    public AuthSettingException(ApplicationErrorType errorType) {
        super(errorType);
    }

    public AuthSettingException(ApplicationErrorType errorType,
        HttpStatus httpStatus) {
        super(errorType, httpStatus);
    }
}
