package com.eod.sitree.member.infra.entity;

import com.eod.sitree.common.infra.entity.BaseEntity;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import jakarta.persistence.Column;
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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String profileImgUrl;

    @Column
    private String thirdPartyProfileUrl;

    @Column
    private String belonging;

    public MemberEntity(Member member) {
        this.memberId = member.getMemberId();
        this.provider = member.getProvider();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.profileImgUrl = member.getProfileImgUrl();
        this.thirdPartyProfileUrl = member.getThirdPartyProfileUrl();
        this.belonging = member.getBelonging();
    }

    public Member toDomainModel() {

        return new Member(
            this.memberId,
            this.provider,
            this.nickname,
            this.email,
            this.profileImgUrl,
            this.thirdPartyProfileUrl,
            this.belonging,
            this.getCreatedAt(),
            this.getModifiedAt()
        );
    }
}
