package com.eod.sitree.common.exception.handler;

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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Map<String, String>>> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> errors = methodArgumentNotValidException.getBindingResult()
            .getAllErrors()
            .stream()
            .collect(Collectors.toMap(
                error -> ((FieldError) error).getField(),
                error ->  Optional.ofNullable(error.getDefaultMessage()).orElse(Strings.EMPTY)
            ));

        log.info("{}\n{}", methodArgumentNotValidException.getMessage(), methodArgumentNotValidException.getStackTrace());
        return getResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<String>> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        log.info("{}\n{}", illegalArgumentException.getMessage(), illegalArgumentException.getStackTrace());
        return getResponse(HttpStatus.BAD_REQUEST, illegalArgumentException.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ResponseDto<String>> handleJwtException(JwtException jwtException) {
        log.error("{}\n{}", jwtException.getMessage(), jwtException.getStackTrace());
        return getResponse(HttpStatus.UNAUTHORIZED, jwtException.getMessage());
    }

    @ExceptionHandler(AuthSettingException.class)
    public void handleAuthSettingException(AuthSettingException authSettingException) {
        log.error("{}\n{}", authSettingException.getErrorMessage(), authSettingException.getStackTrace());
    }

    @ExceptionHandler(CustomException.class)
    public <T> ResponseEntity<ResponseDto<T>> handleCustomException(CustomException customException) {
        log.info("{}\n{}", customException.getErrorMessage(), customException.getStackTrace());
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

    private <T> ResponseEntity<ResponseDto<T>> getResponse(HttpStatus httpStatus, T value) {
        return new ResponseEntity<>(
            new ResponseDto<>(httpStatus, value),
            httpStatus
        );
    }
}
