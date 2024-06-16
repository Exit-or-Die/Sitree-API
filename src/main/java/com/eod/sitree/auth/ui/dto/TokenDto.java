package com.eod.sitree.auth.ui.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.TokenType;

@Getter
public class TokenDto {

    private String accessToken;

    private String refreshToken;

    private Long accessTokenExpiresIn;

    private Long refreshTokenExpiresIn;

    @Builder
    private TokenDto(String accessToken, String refreshToken,
        Long accessTokenExpiresIn, Long refreshTokenExpiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public static TokenDto jwtToken(String accessToken, Long accessTokenExpiresIn, String refreshToken, Long refreshTokenExpiresIn){
        return TokenDto.builder()
            .accessToken(accessToken)
            .accessTokenExpiresIn(accessTokenExpiresIn)
            .refreshToken(refreshToken)
            .refreshTokenExpiresIn(refreshTokenExpiresIn)
            .build();
    }
}
