package com.eod.sitree.auth.ui.dto;

import lombok.Getter;

@Getter
public class SingleTokenDto {

    private String token;

    private Long expiresIn;

    public SingleTokenDto(String token, Long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
