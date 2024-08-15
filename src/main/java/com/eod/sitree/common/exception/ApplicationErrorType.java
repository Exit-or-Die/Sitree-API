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
    FORBIDDEN(403, "FORBIDDEN"),

    INTERNAL_ERROR(10000, "INTERNAL SERVER ERROR"),

    MEMBER_NOT_FOUND(20000, "MEMBER NOT FOUND"),
    MEMBER_ALREADY_EXIST(20001, "MEMBER ALREADY EXIST"),

    AUTH_KEYPAIR_GENERATION_ERROR(30001, "AUTH KEYPAIR GENERATION ERROR"),

    COMMENT_NOT_FOUND(40000, "COMMENT NOT FOUND"),

    ;

    private final int errorCode;
    private final String message;
}
