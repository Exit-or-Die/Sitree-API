package com.eod.sitree.member.domain.model;


import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.common.domain.model.BaseTimeDomain;
import com.eod.sitree.member.infra.entity.MemberEntity;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import com.eod.sitree.member.ui.dto.request.MemberSignUpRequestDto;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

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

    @Nullable
    private String shortIntroduction;

    @Nullable
    private MyPage myPage;


    public Member(MemberEntity memberEntity) {
        super(memberEntity.getCreatedAt(), memberEntity.getModifiedAt());
        this.memberId = memberEntity.getMemberId();
        this.provider = memberEntity.getProvider();
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.profileImgUrl = memberEntity.getProfileImgUrl();
        this.thirdPartyProfileUrl = memberEntity.getThirdPartyProfileUrl();
        this.belongingId = memberEntity.getBelongingId();
        this.myPage = memberEntity.getMyPage();
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
        this.myPage = null;
    }

    public Member(Long memberId, Provider provider, String nickname, String email,
        String profileImgUrl, @Nullable String thirdPartyProfileUrl,
        @Nullable Long belongingId, @Nullable String shortIntroduction, @Nullable MyPage myPage,
        LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(createdAt, modifiedAt);
        this.memberId = memberId;
        this.provider = provider;
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.thirdPartyProfileUrl = thirdPartyProfileUrl;
        this.belongingId = belongingId;
        this.shortIntroduction = shortIntroduction;
        this.myPage = myPage;
    }

    public Member(Provider provider, String nickname, String email, String profileImgUrl,
        @Nullable String thirdPartyProfileUrl,
        @Nullable Long belongingId, @Nullable String shortIntroduction, @Nullable MyPage myPage) {
        super(null, null);
        this.provider = provider;
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.thirdPartyProfileUrl = thirdPartyProfileUrl;
        this.belongingId = belongingId;
        this.shortIntroduction = shortIntroduction;
        this.myPage = myPage;
    }

    public Member() {
        super(null, null);
    }

    public boolean hasBelonging() {

        return belongingId != null;
    }

    public void update(Member updatingMember) {

        if (updatingMember == null) {
            return;
        }

        this.nickname = updatingMember.getNickname();
        this.profileImgUrl = updatingMember.getProfileImgUrl();
        this.thirdPartyProfileUrl = updatingMember.getThirdPartyProfileUrl();
        this.belongingId = updatingMember.getBelongingId();
        this.shortIntroduction = updatingMember.getShortIntroduction();
        this.myPage = updatingMember.getMyPage();
    }

    public List<Long> findBelongingIds() {

        if (this.myPage == null || this.myPage.getCareers() == null) {
            return new ArrayList<>();
        }

        return Optional.ofNullable(this.myPage.getCareers().getCareerList())
            .orElseGet(ArrayList::new)
            .stream()
            .map(Career::getBelongingId)
            .toList();
    }

    public void updateCareerBelonging(Map<Long, Belonging> belongingMap) {

        if (belongingMap == null
            || this.myPage == null
            || this.myPage.getCareers() == null
            || CollectionUtils.isEmpty(this.myPage.getCareers().getCareerList())
        ) {

            return;
        }

        this.myPage.getCareers().getCareerList()
            .forEach(career -> career.updateBelonging(belongingMap.get(career.getBelongingId())));
    }

    public void updateCareerPeriod() {

        if (this.myPage == null || this.myPage.getCareers() == null
            || CollectionUtils.isEmpty(this.myPage.getCareers().getCareerList())) {

            return;
        }

        Period totalPeriod = Period.ZERO;

        List<Career> careers = this.myPage.getCareers().getCareerList();
        for (Career career : careers) {
            Period between = Period.between(
                career.findStartedAtOrElseNow().toLocalDate(),
                career.findEndedAtOrElseNow().toLocalDate()
            );

            totalPeriod = totalPeriod.plus(between);
        }

        this.myPage.getCareers().updatePeriod(totalPeriod);
    }
}
