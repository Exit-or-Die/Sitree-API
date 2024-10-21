package com.eod.sitree.member.domain.modelrepository;

import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByProviderAndEmailOptional(Provider provider, String email);

    Member findByProviderAndEmail(Provider provider, String email);

    Member save(Member member);

    Optional<Member> findByNicknameOptional(String nickname);

    Boolean isNicknameExist(String nickname);

    Optional<Member> findByMemberId(Long memberId);
}
