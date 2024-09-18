package com.eod.sitree.comment.exception;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CommentException extends CustomException {

    public CommentException(ApplicationErrorType errorType) {
        super(errorType);
    }

    public CommentException(ApplicationErrorType errorType,
        HttpStatus httpStatus) {
        super(errorType, httpStatus);
    }
}
