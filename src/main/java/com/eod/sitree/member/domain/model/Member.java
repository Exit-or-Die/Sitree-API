package com.eod.sitree.member.domain.model;


import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import lombok.Getter;

@Getter
public class Member {

    private Long memberNo;

    private String authId;

    private String nickname;

    private String email;

    private String profileImgUrl;


    public Member(Long memberNo, String authId, String nickname, String email,
        String profileImgUrl) {
        this.memberNo = memberNo;
        this.authId = authId;
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }

    public static Member ofSignUp(MemberSignDto memberSignDto) {
        return new Member(
            null,
            memberSignDto.getAuthId(),
            memberSignDto.getNickname(),
            memberSignDto.getEmail(),
            memberSignDto.getProfileImgUrl()
            );
    }
}
