package com.eod.sitree.auth.domain;

import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberClaim {

    private Provider provider;

    private String email;

    public MemberClaim(Member member) {
        this.provider = member.getProvider();
        this.email = member.getEmail();
    }

    public MemberClaim(Provider provider, String email) {
        this.provider = provider;
        this.email = email;
    }
}
