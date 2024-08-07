package com.eod.sitree.member.infra.entity;

import com.eod.sitree.common.infra.entity.BaseEntity;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
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
    private Long memberNo;

    @Column(nullable = false)
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
        this.memberNo = member.getMemberNo();
        this.provider = member.getProvider();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.profileImgUrl = member.getProfileImgUrl();
        this.thirdPartyProfileUrl = member.getThirdPartyProfileUrl();
        this.belonging = member.getBelonging();
    }
}
