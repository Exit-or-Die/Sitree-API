package com.eod.sitree.belonging.exception;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;

public class BelongingException extends CustomException {

    public BelongingException(ApplicationErrorType applicationErrorType) {
        super(applicationErrorType);
    }
}
