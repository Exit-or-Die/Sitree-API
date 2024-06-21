package com.eod.sitree.auth.ui.dto;

import com.eod.sitree.auth.domain.JwtToken;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.TokenType;

@Getter
public class TokenDto {

    private String accessToken;

    private String refreshToken;

    public TokenDto(JwtToken accessToken, JwtToken refreshToken) {
        this.accessToken = accessToken.getTokenValue();
        this.refreshToken = refreshToken.getTokenValue();
    }
}
