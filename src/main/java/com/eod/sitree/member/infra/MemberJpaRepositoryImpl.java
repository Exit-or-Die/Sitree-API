package com.eod.sitree.member.infra;

import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.member.infra.entity.MemberEntity;
import java.util.Optional;
import org.springframework.stereotype.Component;

/**
 * JPA repository를 사용하기 위한 Adaptor
 */
@Component
public class MemberJpaRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    public MemberJpaRepositoryImpl(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Optional<Member> findByAuthIdAndEmailOptional(String authId, String email) {
        Optional<MemberEntity> memberEntityOptional = Optional.ofNullable( memberJpaRepository.findByAuthIdAndEmail(authId, email));

        return memberEntityOptional.map(MemberEntity::toModel);
    }
}
