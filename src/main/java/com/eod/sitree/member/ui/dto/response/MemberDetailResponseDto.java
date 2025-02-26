package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.MyPage;
import lombok.Getter;

@Getter
public class MemberDetailResponseDto {

    private Long memberId;

    private String nickname;

    private String email;

    private String profileImgUrl;

    private String thirdPartyProfileUrl;

    private Long belongingId;

    private String belongingName;

    private MyPage myPage;

    public MemberDetailResponseDto(Long memberId, String nickname, String email,
        String profileImgUrl, String thirdPartyProfileUrl, Long belongingId, String belongingName,
        MyPage myPage) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.thirdPartyProfileUrl = thirdPartyProfileUrl;
        this.belongingId = belongingId;
        this.belongingName = belongingName;
        this.myPage = myPage;
    }

    public MemberDetailResponseDto(Member member, String belongingName) {
        this.memberId = member.getMemberId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.profileImgUrl = member.getProfileImgUrl();
        this.thirdPartyProfileUrl = member.getThirdPartyProfileUrl();
        this.belongingId = member.getBelongingId();
        this.belongingName = belongingName;
        this.myPage = member.getMyPage();
    }
}
