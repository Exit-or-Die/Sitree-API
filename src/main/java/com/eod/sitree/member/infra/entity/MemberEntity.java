package com.eod.sitree.member.infra.entity;

import com.eod.sitree.common.infra.entity.BaseEntity;
import com.eod.sitree.member.domain.model.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

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

    public Member toModel() {
        return new Member(memberId, authId, nickname, email, profileImgUrl);
    }

    public static MemberEntity from(Member member) {
        return new MemberEntity(
            member.getMemberNo(),
            member.getAuthId(),
            member.getNickname(),
            member.getEmail(),
            member.getProfileImgUrl()
        );
    }
}
