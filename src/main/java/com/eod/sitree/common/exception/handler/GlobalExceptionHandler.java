package com.eod.sitree.common.exception.handler;

import com.eod.sitree.auth.exception.AuthException;
import com.eod.sitree.auth.exception.AuthSettingException;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;
import com.eod.sitree.common.response.ResponseDto;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.lang.Strings;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Map<String, String>>> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {

        Map<String, String> errors = e.getBindingResult()
            .getAllErrors()
            .stream()
            .collect(Collectors.toMap(
                error -> ((FieldError) error).getField(),
                error ->  Optional.ofNullable(error.getDefaultMessage()).orElse(Strings.EMPTY)
            ));

        log.info("{}", e.getMessage());
        e.printStackTrace(System.err);
        return getResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.info("{}", e.getMessage());
        e.printStackTrace(System.err);
        return getResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDto<String>> handleIllegalArgumentException(HttpRequestMethodNotSupportedException e) {
        log.info("{}", e.getMessage());
        e.printStackTrace(System.err);
        return getResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto<String>> handleIllegalArgumentException(
        HttpMessageNotReadableException e) {
        log.info("{}", e.getMessage());
        e.printStackTrace(System.err);
        return getResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ResponseDto<String>> handleJwtException(JwtException e) {
        log.info("{}", e.getMessage());
        e.printStackTrace(System.err);
        return getResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public <T> ResponseEntity<ResponseDto<T>> handleAuthException(AuthException e) {
        log.info("{}", e.getMessage());
        e.printStackTrace(System.err);
        return getErrorResponse(e);
    }

    @ExceptionHandler(AuthSettingException.class)
    public void handleAuthSettingException(AuthSettingException e) {
        log.info("{}", e.getMessage());
        e.printStackTrace(System.err);
    }

    @ExceptionHandler(CustomException.class)
    public <T> ResponseEntity<ResponseDto<T>> handleCustomException(CustomException e) {
        log.info("{}", e.getMessage());
        e.printStackTrace(System.err);
        return getErrorResponse(e);
    }

    /**
     * 위에 해당하는 예외에 해당하지 않을 때 모든 예외를 처리하는 메소드
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> ResponseDto<T> handleUnexpectedException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace(System.err);
        return ResponseDto.error(ApplicationErrorType.INTERNAL_ERROR);
    }

    private <T> ResponseEntity<ResponseDto<T>> getErrorResponse(CustomException customException) {
        return new ResponseEntity<>(
                ResponseDto.error(customException.getErrorType()),
                customException.getHttpStatus()
        );
    }

    private <T> ResponseEntity<ResponseDto<T>> getResponse(HttpStatus httpStatus, T value) {
        return new ResponseEntity<>(
            new ResponseDto<>(httpStatus, value),
            httpStatus
        );
    }
}
