package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponseDto extends MemberSignDto {

    private Boolean isNewMember;

    private String accessToken;

    private String refreshToken;


    @Builder
    private SignInResponseDto(Provider provider, String email, String nickname, String profileImgUrl, Boolean isNewMember, String accessToken, String refreshToken) {
        super(provider, email, nickname, profileImgUrl);
        this.isNewMember = isNewMember;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static SignInResponseDto ofNotNewMember(Member member) {
        JwtToken accessToken = new JwtToken(member, JwtTokenType.ACCESS_TOKEN, AuthService.jwtKeypair);
        JwtToken refreshToken = new JwtToken(member, JwtTokenType.REFRESH_TOKEN,

            AuthService.jwtKeypair);
        return SignInResponseDto.builder()
            .provider(member.getProvider())
            .email(member.getEmail())
            .nickname(member.getNickname())
            .profileImgUrl(member.getProfileImgUrl())
            .isNewMember(false)
            .accessToken(accessToken.getTokenValue())
            .refreshToken(refreshToken.getTokenValue())
            .build();
    }

    public static SignInResponseDto ofNewMember(MemberSignDto memberSignDto) {
        return SignInResponseDto.builder()
            .provider(memberSignDto.getProvider())
            .email(memberSignDto.getEmail())
            .nickname(memberSignDto.getNickname())
            .profileImgUrl(memberSignDto.getProfileImgUrl())
            .isNewMember(true)
            .build();
    }

}
