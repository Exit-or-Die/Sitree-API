package com.eod.sitree.common.auth.domain;

import com.eod.sitree.member.domain.model.Member;
import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public class MemberClaim {

    private String authId;

    private String email;

    public MemberClaim(String authId, String email) {
        this.authId = authId;
        this.email = email;
    }

    public static MemberClaim from(Member member) {
        return new MemberClaim(member.getAuthId(), member.getEmail());
    }
}
