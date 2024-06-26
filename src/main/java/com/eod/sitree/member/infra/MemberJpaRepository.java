package com.eod.sitree.member.infra;

import com.eod.sitree.member.infra.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByAuthIdAndEmail(String authId, String email);
}
