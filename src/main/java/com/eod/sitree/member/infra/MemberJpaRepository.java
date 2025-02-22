package com.eod.sitree.member.infra;

import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.infra.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByProviderAndEmail(Provider provider, String email);

    MemberEntity findByEmail(String email);

    MemberEntity findByNickname(String nickname);
}
