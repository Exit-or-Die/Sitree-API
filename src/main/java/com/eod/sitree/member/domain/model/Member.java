package com.eod.sitree.member.domain.model;


import com.eod.sitree.member.infra.entity.MemberEntity;
import lombok.AllArgsConstructor;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {

    private Long memberNo;

    private Provider provider;

    private String nickname;

    private String email;

    private String profileImgUrl;


    public Member(MemberEntity memberEntity) {
        this.memberNo = memberEntity.getMemberNo();
        this.provider = memberEntity.getProvider();
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.profileImgUrl = memberEntity.getProfileImgUrl();
    }

    public Member(MemberSignDto memberSignDto) {

        this.memberNo = null;
        this.provider = memberSignDto.getProvider();
        this.nickname = memberSignDto.getNickname();
        this.email = memberSignDto.getEmail();
        this.profileImgUrl = memberSignDto.getProfileImgUrl();
    }
}
