package com.eod.sitree.member.domain.model;


import com.eod.sitree.member.infra.entity.MemberEntity;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import com.eod.sitree.member.ui.dto.request.MemberSignUpRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private Long memberId;

    private Provider provider;

    private String nickname;

    private String email;

    private String profileImgUrl;

    @Nullable
    private String thirdPartyProfileUrl;

    @Nullable
    private String belonging;


    public Member(MemberEntity memberEntity) {
        this.memberId = memberEntity.getMemberId();
        this.provider = memberEntity.getProvider();
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.profileImgUrl = memberEntity.getProfileImgUrl();
        this.thirdPartyProfileUrl = memberEntity.getThirdPartyProfileUrl();
        this.belonging = memberEntity.getBelonging();
    }

    public Member(MemberSignUpRequestDto memberSignUpRequestDto) {

        this.memberId = null;
        this.provider = memberSignUpRequestDto.getProvider();
        this.nickname = memberSignUpRequestDto.getNickname();
        this.email = memberSignUpRequestDto.getEmail();
        this.profileImgUrl = memberSignUpRequestDto.getProfileImgUrl();
        this.thirdPartyProfileUrl = memberSignUpRequestDto.getThirdPartyProfileUrl();
        this.belonging = memberSignUpRequestDto.getBelonging();
    }
}
