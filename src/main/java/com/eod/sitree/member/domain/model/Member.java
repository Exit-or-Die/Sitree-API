package com.eod.sitree.member.domain.model;


import com.eod.sitree.member.infra.entity.MemberEntity;
import lombok.Getter;

@Getter
public class Member {

    private Long memberId;

    private String authId;

    private String nickname;

    private String email;

    private String profileImgUrl;


    public Member(Long memberId, String authId, String nickname, String email,
        String profileImgUrl) {
        this.memberId = memberId;
        this.authId = authId;
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }

    public Member(MemberEntity memberEntity) {
        this.memberId = memberEntity.getMemberId();
        this.authId = memberEntity.getAuthId();
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.profileImgUrl = memberEntity.getProfileImgUrl();
    }
}
