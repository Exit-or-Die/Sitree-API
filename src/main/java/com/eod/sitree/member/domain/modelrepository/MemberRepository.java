package com.eod.sitree.member.domain.modelrepository;

import com.eod.sitree.member.domain.model.Member;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByAuthIdAndEmailOptional(String authId, String email);

}
