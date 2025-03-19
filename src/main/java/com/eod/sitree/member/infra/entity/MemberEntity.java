package com.eod.sitree.member.infra.entity;

import com.eod.sitree.common.converter.MyPageConverter;
import com.eod.sitree.common.infra.entity.BaseEntity;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.MyPage;
import com.eod.sitree.member.domain.model.Provider;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(
    name = "tb_member",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "member_unique_key",
            columnNames = {"provider", "email"}
        )
    }
)
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String position;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String phoneNumber;

    @Column(nullable = false)
    private String profileImgUrl;

    @Column
    private String thirdPartyProfileUrl;

    @Column
    private Long belongingId;

    @Column
    private String shortIntroduction;

    @Column
    @Convert(converter = MyPageConverter.class)
    private MyPage myPage;

    public MemberEntity(Member member) {
        this.memberId = member.getMemberId();
        this.provider = member.getProvider();
        this.nickname = member.getNickname();
        this.position = member.getPosition();
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
        this.profileImgUrl = member.getProfileImgUrl();
        this.thirdPartyProfileUrl = member.getThirdPartyProfileUrl();
        this.belongingId = member.getBelongingId();
        this.shortIntroduction = member.getShortIntroduction();
        this.myPage = member.getMyPage();
    }

    public Member toDomainModel() {

        return new Member(
            this.memberId,
            this.provider,
            this.nickname,
            this.position,
            this.email,
            this.phoneNumber,
            this.profileImgUrl,
            this.thirdPartyProfileUrl,
            this.belongingId,
            this.shortIntroduction,
            this.myPage,
            this.getCreatedAt(),
            this.getModifiedAt()
        );
    }
}
