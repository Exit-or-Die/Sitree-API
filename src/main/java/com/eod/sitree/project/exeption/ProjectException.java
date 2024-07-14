package com.eod.sitree.project.exeption;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ProjectException extends CustomException {

    public ProjectException(ApplicationErrorType errorType) {
        super(errorType);
    }

    public ProjectException(ApplicationErrorType errorType,
            HttpStatus httpStatus) {
        super(errorType, httpStatus);
    }
}
