package com.eod.sitree.common.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException() {

        super(ApplicationErrorType.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }
}
