package com.eod.sitree.member.domain.model;


import com.eod.sitree.common.domain.model.BaseTimeDomain;
import com.eod.sitree.member.infra.entity.MemberEntity;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import com.eod.sitree.member.ui.dto.request.MemberSignUpRequestDto;
import lombok.Getter;

@Getter
public class Member extends BaseTimeDomain {

    private Long memberId;

    private Provider provider;

    private String nickname;

    private String email;

    private String profileImgUrl;

    @Nullable
    private String thirdPartyProfileUrl;

    @Nullable
    private Long belongingId;


    public Member(MemberEntity memberEntity) {
        super(memberEntity.getCreatedAt(), memberEntity.getModifiedAt());
        this.memberId = memberEntity.getMemberId();
        this.provider = memberEntity.getProvider();
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.profileImgUrl = memberEntity.getProfileImgUrl();
        this.thirdPartyProfileUrl = memberEntity.getThirdPartyProfileUrl();
        this.belongingId = memberEntity.getBelongingId();
    }

    public Member(MemberSignUpRequestDto memberSignUpRequestDto, String baseProfileImgUrl) {
        super(LocalDateTime.now(), null);
        this.memberId = null;
        this.provider = memberSignUpRequestDto.getProvider();
        this.nickname = memberSignUpRequestDto.getNickname();
        this.email = memberSignUpRequestDto.getEmail();
        this.profileImgUrl = baseProfileImgUrl;
        this.thirdPartyProfileUrl = memberSignUpRequestDto.getThirdPartyProfileUrl();
        this.belongingId = memberSignUpRequestDto.getBelongingId();
    }

    public Member(Long memberId, Provider provider, String nickname, String email, String profileImgUrl, @Nullable String thirdPartyProfileUrl,
        @Nullable Long belongingId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(createdAt, modifiedAt);
        this.memberId = memberId;
        this.provider = provider;
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.thirdPartyProfileUrl = thirdPartyProfileUrl;
        this.belongingId = belongingId;
    }

    public boolean hasBelonging() {

        return belongingId != null;
    }
}
