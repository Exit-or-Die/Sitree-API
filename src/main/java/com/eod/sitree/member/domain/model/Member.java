package com.eod.sitree.member.domain.model;


import lombok.Getter;

@Getter
public class Member {

    private Long memberId;

    private String authId;

    private String nickName;

    private String email;

    private String profileImgUrl;


    public Member(Long memberId, String authId, String nickName, String email,
        String profileImgUrl) {
        this.memberId = memberId;
        this.authId = authId;
        this.nickName = nickName;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }
}
