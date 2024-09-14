package com.eod.sitree.member.infra;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.member.exception.MemberException;
import com.eod.sitree.member.infra.entity.MemberEntity;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;


/**
 * JPA repository를 사용하기 위한 Adaptor
 */
@Repository
public class JpaMemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    public JpaMemberRepositoryImpl(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Optional<Member> findByProviderAndEmailOptional(Provider provider, String email) {
        Optional<MemberEntity> memberEntityOptional = Optional.ofNullable(
            memberJpaRepository.findByProviderAndEmail(provider, email));

        return memberEntityOptional.map(Member::new);
    }

    @Override
    public Member findByProviderAndEmail(Provider provider, String email) {
        MemberEntity memberEntity = memberJpaRepository.findByProviderAndEmail(provider, email);

        if (Objects.isNull(memberEntity)) {
            throw new MemberException(ApplicationErrorType.MEMBER_NOT_FOUND);
        }

        return new Member(memberEntity);
    }

    @Override
    public Member save(Member member) {
        MemberEntity savedMemberEntity = memberJpaRepository.save(new MemberEntity(member));

        return new Member(savedMemberEntity);
    }

    @Override
    public Optional<Member> findByNicknameOptional(String nickname) {

        return Optional.ofNullable(memberJpaRepository.findByNickname(nickname))
            .map(MemberEntity::toDomainModel);
    }

    @Override
    public Boolean isNicknameExist(String nickname) {

        return findByNicknameOptional(nickname).isPresent();
    }
}
