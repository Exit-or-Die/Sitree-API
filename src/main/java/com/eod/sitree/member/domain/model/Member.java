package com.eod.sitree.member.domain.model;


import com.eod.sitree.member.infra.entity.MemberEntity;
import lombok.AllArgsConstructor;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {

    private Long memberNo;

    private String authId;

    private String nickname;

    private String email;

    private String profileImgUrl;



    public Member(MemberEntity memberEntity) {
        this.memberNo = memberEntity.getMemberNo();
        this.authId = memberEntity.getAuthId();
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.profileImgUrl = memberEntity.getProfileImgUrl();
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
