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

    // Member Exception Code (30000~)
    MEMBER_NOT_FOUND(20000, "MEMBER NOT FOUND"),
    MEMBER_ALREADY_EXIST(20001, "MEMBER ALREADY EXIST"),

    AUTH_KEYPAIR_GENERATION_ERROR(30001, "AUTH KEYPAIR GENERATION ERROR"),

    // Image Exception Code (40000~)
    IMAGE_UPLOAD_FAIL(40001, "Image Upload Fail"),

    // Project Exception Code (50000~)
    CHECK_CLIENT_URL_REQUIRED_VALUE(50000, "LIVE_DOMAIN or DOWNLOAD_LINk is needed"),
    CHECK_HEAD_REQUIRED_VALUE(50001, "THUMBNAIL or TITLE is needed"),
    NO_REPRESENT_IMAGE(50002, "NO REPRESENT IMAGE IN OVERVIEW"),
    NOT_EXIST_PROJECT_WITH_SUCH_PROJECT_ID(50003, "No project with such project_id"),

    // Comment Exception Code (60000~)
    COMMENT_NOT_FOUND(60000, "COMMENT NOT FOUND"),
    COMMENT_CREATE_MEMBER_NOT_MATCH(60001, "COMMENT CREATE MEMBER NOT MATCH"),
    COMMENT_NOT_PARENT(60002, "THIS COMMENT IS NOT PARENT"),
    PARENT_COMMENT_NOT_FOUND(60003, "PARENT COMMENT NOT FOUND"),
    PARENT_COMMENT_CANNOT_HAVE_PARENT(60004, "PARENT COMMENT CANNOT HAVE PARENT"),


    ;

    private final int errorCode;
    private final String message;
}
