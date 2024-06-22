package com.eod.sitree.common.response;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private final int code;
    private final String message;
    private final T value;

    public ResponseDto(T value) {
        this.code = 0;
        this.message = null;
        this.value = value;
    }
    public ResponseDto(ApplicationErrorType errorType) {
        this.code = errorType.getErrorCode();
        this.message = errorType.getMessage();
        this.value = null;
    }
    public ResponseDto(HttpStatus httpStatus, T value) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.value = value;
    }

    public static <T> ResponseDto<T> ok(T value) {
        return new ResponseDto<>(value);
    }

    public static <T> ResponseDto<T> error(ApplicationErrorType errorType) {
        return new ResponseDto<>(errorType);
    }
}

