package com.eod.sitree.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationErrorType {
    /**
     * Test Error Type
     */

    BAD_REQUEST (400, "BAD REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),

    INTERNAL_ERROR(10000, "에러메시지 예시입니다."),

    MEMBER_NOT_FOUND(20000, "MEMBER NOT FOUND"),
    MEMBER_ALREADY_EXIST(20001, "MEMBER ALREADY EXIST"),

    AUTH_KEYPAIR_GENERATION_ERROR(30001, "AUTH KEYPAIR GENERATION ERROR"),

    ;

    private final int errorCode;
    private final String message;
}
