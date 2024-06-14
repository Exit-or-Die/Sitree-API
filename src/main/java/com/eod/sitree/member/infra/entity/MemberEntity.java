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
    private String nickName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String profileImgUrl;

    public MemberEntity(Long memberId, String authId, String nickName, String email,
        String profileImgUrl) {
        this.memberId = memberId;
        this.authId = authId;
        this.nickName = nickName;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }

    public Member toModel() {
        return new Member(memberId, authId, nickName, email, profileImgUrl);
    }

    public static MemberEntity from(Member member) {
        return new MemberEntity(
            member.getMemberId(),
            member.getAuthId(),
            member.getNickName(),
            member.getEmail(),
            member.getProfileImgUrl()
        );
    }
}
