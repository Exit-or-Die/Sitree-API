package com.eod.sitree.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationErrorType {
    /**
     * Test Error Type
     */
    INTERNAL_ERROR(10000, "에러메시지 예시입니다."),

    MEMBER_NOT_FOUND(20000, "MEMBER NOT FOUND"),

    ;

    private final int errorCode;
    private final String message;
}
