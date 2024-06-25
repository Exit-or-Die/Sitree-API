package com.eod.sitree.member.exception;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class MemberException extends CustomException {

    public MemberException(ApplicationErrorType errorType) {
        super(errorType);
    }

    public MemberException(ApplicationErrorType errorType,
        HttpStatus httpStatus) {
        super(errorType, httpStatus);
    }
}
