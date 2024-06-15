package com.eod.sitree.common.exception.handler;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;
import com.eod.sitree.common.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public <T> ResponseEntity<ResponseDto<T>> handelCustomException(CustomException customException) {
        log.info(customException.getErrorMessage());
        return getErrorResponse(customException);
    }

    /**
     * 위에 해당하는 예외에 해당하지 않을 때 모든 예외를 처리하는 메소드
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> ResponseDto<T> handleUnexpectedException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseDto.error(ApplicationErrorType.INTERNAL_ERROR);
    }

    private <T> ResponseEntity<ResponseDto<T>> getErrorResponse(CustomException customException) {
        return new ResponseEntity<>(
                ResponseDto.error(customException.getErrorType()),
                customException.getHttpStatus()
        );
    }
}
