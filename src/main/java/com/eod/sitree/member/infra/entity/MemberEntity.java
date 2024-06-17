package com.eod.sitree.member.infra.entity;

import com.eod.sitree.common.infra.entity.BaseEntity;
import com.eod.sitree.member.domain.model.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true, nullable = false)
    private String authId;

    @Column(nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String profileImgUrl;

    public MemberEntity(Long memberId, String authId, String nickname, String email,
        String profileImgUrl) {
        this.memberId = memberId;
        this.authId = authId;
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }

    public MemberEntity(Member member) {

        this.memberId = member.getMemberId();
        this.authId = member.getAuthId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.profileImgUrl = member.getProfileImgUrl();
    }
}
