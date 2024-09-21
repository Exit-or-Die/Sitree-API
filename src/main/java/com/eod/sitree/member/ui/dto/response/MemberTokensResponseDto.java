package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberTokensResponseDto {

    private Long memberId;

    private Provider provider;

    private String email;

    private String nickname;

    private String profileImgUrl;

    private String accessToken;

    private String refreshToken;

    public MemberTokensResponseDto(Member member, String accessToken, String refreshToken) {

        this.memberId = member.getMemberId();
        this.provider = member.getProvider();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.profileImgUrl = member.getProfileImgUrl();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public MemberTokensResponseDto(Member member) {

        JwtToken accessToken = new JwtToken(member, JwtTokenType.ACCESS_TOKEN, AuthService.jwtKeypair);
        JwtToken refreshToken = new JwtToken(member, JwtTokenType.REFRESH_TOKEN, AuthService.jwtKeypair);

        this.memberId = member.getMemberId();
        this.provider = member.getProvider();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.profileImgUrl = member.getProfileImgUrl();
        this.accessToken = accessToken.getTokenValue();
        this.refreshToken = refreshToken.getTokenValue();

    }

    public static MemberTokensResponseDto expired(Member member) {

        JwtToken expired = JwtToken.expired(member, JwtTokenType.ACCESS_TOKEN, AuthService.jwtKeypair);
        JwtToken refreshToken = new JwtToken(member, JwtTokenType.REFRESH_TOKEN, AuthService.jwtKeypair);

        return new MemberTokensResponseDto(
            member.getMemberId(),
            member.getProvider(),
            member.getEmail(),
            member.getNickname(),
            member.getProfileImgUrl(),
            expired.getTokenValue(),
            refreshToken.getTokenValue()
        );
    }

}
