package com.eod.sitree.member.domain.model;


import com.eod.sitree.member.infra.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {

    private Long memberId;

    private String authId;

    private String nickname;

    private String email;

    private String profileImgUrl;



    public Member(MemberEntity memberEntity) {
        this.memberId = memberEntity.getMemberId();
        this.authId = memberEntity.getAuthId();
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.profileImgUrl = memberEntity.getProfileImgUrl();
    }
}
