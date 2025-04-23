package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.MyPage;
import java.util.Optional;
import lombok.Getter;

@Getter
public class MemberDetailResponseDto {

    private Long memberId;

    private String nickname;

    private String position;

    private String email;

    private String phoneNumber;

    private String profileImgUrl;

    private String thirdPartyProfileUrl;

    private String shortIntroduction;

    private Long belongingId;

    private String belongingName;

    private MyPage myPage;

    public MemberDetailResponseDto(Long memberId, String nickname, String position, String email, String phoneNumber,
        String profileImgUrl, String thirdPartyProfileUrl, String shortIntroduction, Long belongingId, String belongingName,
        MyPage myPage) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImgUrl = profileImgUrl;
        this.thirdPartyProfileUrl = thirdPartyProfileUrl;
        this.shortIntroduction = shortIntroduction;
        this.belongingId = belongingId;
        this.belongingName = belongingName;
        this.myPage = Optional.ofNullable(myPage).orElseGet(MyPage::new).switchNullAsEmptyAndReturn();
    }

    public MemberDetailResponseDto(Member member, String belongingName) {
        this.memberId = member.getMemberId();
        this.nickname = member.getNickname();
        this.position = member.getPosition();
        this.phoneNumber = member.getPhoneNumber();
        this.email = member.getEmail();
        this.profileImgUrl = member.getProfileImgUrl();
        this.thirdPartyProfileUrl = member.getThirdPartyProfileUrl();
        this.belongingId = member.getBelongingId();
        this.belongingName = belongingName;
        this.myPage = member.getMyPage();
    }
}
