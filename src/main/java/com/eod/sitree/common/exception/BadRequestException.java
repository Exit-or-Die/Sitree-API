package com.eod.sitree.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

    public BadRequestException() {

        super(ApplicationErrorType.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }
}
