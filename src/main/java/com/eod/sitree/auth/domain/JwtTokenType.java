package com.eod.sitree.auth.domain;

import lombok.Getter;

@Getter
public enum JwtTokenType {
    ACCESS_TOKEN("x-access-token", 1000L * 60L * 30L),
    REFRESH_TOKEN("x-refresh-token", 1000L * 60L * 60L * 24L * 7L)
    ;

    private final String headerName;
    private final Long expireInMilliSecond;

    JwtTokenType(String headerName, Long expireInMilliSecond) {
        this.headerName = headerName;
        this.expireInMilliSecond = expireInMilliSecond;
    }
}
