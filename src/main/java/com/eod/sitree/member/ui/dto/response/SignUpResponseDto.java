package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.auth.ui.dto.TokenDto;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import lombok.Builder;

public class SignUpResponseDto extends MemberSignDto {

    private Long memberNo;

    private TokenDto tokens;


    @Builder
    private SignUpResponseDto(String authId, String email, String nickname, String profileImgUrl,
        Long memberNo, TokenDto tokens) {
        super(authId, email, nickname, profileImgUrl);
        this.memberNo = memberNo;
        this.tokens = tokens;
    }

    public static SignUpResponseDto from(Member member, TokenDto tokens) {
        return SignUpResponseDto.builder()
            .memberNo(member.getMemberNo())
            .authId(member.getAuthId())
            .email(member.getEmail())
            .nickname(member.getNickname())
            .profileImgUrl(member.getProfileImgUrl())
            .tokens(tokens)
            .build();
    }

}
