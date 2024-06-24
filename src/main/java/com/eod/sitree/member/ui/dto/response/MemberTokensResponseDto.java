package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;

public class MemberTokensResponseDto extends MemberSignDto {

    private Long memberNo;

    private String accessToken;

    private String refreshToken;

    public MemberTokensResponseDto(Member member, String accessToken, String refreshToken) {
        super(member.getAuthId(), member.getEmail(), member.getNickname(), member.getProfileImgUrl());
        this.memberNo = member.getMemberNo();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public MemberTokensResponseDto(Member member) {
        super(member.getAuthId(), member.getEmail(), member.getNickname(), member.getProfileImgUrl());

        JwtToken accessToken = new JwtToken(member, JwtTokenType.ACCESS_TOKEN, AuthService.jwtKeypair);
        JwtToken refreshToken = new JwtToken(member, JwtTokenType.REFRESH_TOKEN, AuthService.jwtKeypair);

        this.memberNo = member.getMemberNo();
        this.accessToken = accessToken.getTokenValue();
        this.refreshToken = refreshToken.getTokenValue();

    }

}
