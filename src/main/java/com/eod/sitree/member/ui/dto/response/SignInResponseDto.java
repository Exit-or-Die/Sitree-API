package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.ui.dto.request.MemberSignInRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponseDto {

    private Long memberId;

    private Provider provider;

    private String email;

    private String nickname;

    private String profileImgUrl;

    private Boolean isNewMember;

    private String accessToken;

    private String refreshToken;


    @Builder
    private SignInResponseDto(Long memberId, Provider provider, String email, String nickname, String profileImgUrl, Boolean isNewMember, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.provider = provider;
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
        this.isNewMember = isNewMember;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static SignInResponseDto ofNotNewMember(Member member) {
        JwtToken accessToken = new JwtToken(member, JwtTokenType.ACCESS_TOKEN, AuthService.jwtKeypair);
        JwtToken refreshToken = new JwtToken(member, JwtTokenType.REFRESH_TOKEN, AuthService.jwtKeypair);

        return SignInResponseDto.builder()
            .memberId(member.getMemberId())
            .provider(member.getProvider())
            .email(member.getEmail())
            .nickname(member.getNickname())
            .profileImgUrl(member.getProfileImgUrl())
            .isNewMember(false)
            .accessToken(accessToken.getTokenValue())
            .refreshToken(refreshToken.getTokenValue())
            .build();
    }

    public static SignInResponseDto ofNewMember(MemberSignInRequestDto memberSignInRequestDto) {
        return SignInResponseDto.builder()
            .provider(memberSignInRequestDto.getProvider())
            .email(memberSignInRequestDto.getEmail())
            .isNewMember(true)
            .build();
    }
}
