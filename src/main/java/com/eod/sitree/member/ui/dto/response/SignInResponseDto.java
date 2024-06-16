package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import lombok.Builder;

public class SignInResponseDto extends MemberSignDto {

    private Boolean isNewMember;


    @Builder
    private SignInResponseDto(String authId, String email, String nickname, String profileImgUrl, Boolean isNewMember) {
        super(authId, email, nickname, profileImgUrl);
    }

    public static SignInResponseDto ofNotNewMember(Member member) {
        return SignInResponseDto.builder()
            .authId(member.getAuthId())
            .email(member.getEmail())
            .nickname(member.getNickname())
            .profileImgUrl(member.getProfileImgUrl())
            .isNewMember(false)
            .build();
    }

    public static SignInResponseDto ofNewMember(MemberSignDto memberSignDto) {
        return SignInResponseDto.builder()
            .authId(memberSignDto.getAuthId())
            .email(memberSignDto.getEmail())
            .nickname(memberSignDto.getNickname())
            .profileImgUrl(memberSignDto.getProfileImgUrl())
            .isNewMember(true)
            .build();
    }

}
