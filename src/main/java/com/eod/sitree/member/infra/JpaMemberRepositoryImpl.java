package com.eod.sitree.member.infra;

import com.eod.sitree.belonging.infra.entity.QBelongingEntity;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.member.exception.MemberException;
import com.eod.sitree.member.infra.entity.MemberEntity;
import com.eod.sitree.member.infra.entity.QMemberEntity;
import com.eod.sitree.member.ui.dto.response.MemberDetailResponseDto;
import com.eod.sitree.member.ui.dto.response.MemberSearchPageResponse.MemberSearchResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


/**
 * JPA repository를 사용하기 위한 Adaptor
 */
@Repository
public class JpaMemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final MemberJpaRepository memberJpaRepository;

    private final static QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

    public JpaMemberRepositoryImpl(JPAQueryFactory jpaQueryFactory,
        MemberJpaRepository memberJpaRepository) {

        super(MemberEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Optional<Member> findByProviderAndEmailOptional(Provider provider, String email) {
        Optional<MemberEntity> memberEntityOptional = Optional.ofNullable(
            memberJpaRepository.findByProviderAndEmail(provider, email));

        return memberEntityOptional.map(Member::new);
    }

    @Override
    public Optional<Member> findByEmailOptional(String email) {
        Optional<MemberEntity> memberEntityOptional = Optional.ofNullable(
            memberJpaRepository.findByEmail(email));

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

    @Override
    public Page<Member> searchMembers(String q, Pageable pageable) {

        JPQLQuery<MemberEntity> query = getQuerydsl().applyPagination(
            pageable,
            jpaQueryFactory.selectFrom(qMemberEntity)
                .where(
                    qMemberEntity.nickname.like(q + "%")
                )
        );

        return new PageImpl<>(
            query.fetch().stream().map(MemberEntity::toDomainModel).toList(),
            pageable,
            query.fetchCount()
        );
    }

    @Override
    public Page<MemberSearchResponseDto> searchMembersAsDto(String q, Pageable pageable) {

        QBelongingEntity qBelongingEntity = new QBelongingEntity("belonging");

        List<MemberSearchResponseDto> result = jpaQueryFactory.select(
                Projections.constructor(
                    MemberSearchResponseDto.class,
                    qMemberEntity.memberId,
                    qMemberEntity.nickname,
                    qMemberEntity.email,
                    qMemberEntity.profileImgUrl,
                    qMemberEntity.thirdPartyProfileUrl,
                    qMemberEntity.belongingId,
                    qBelongingEntity.name
                )
            )
            .from(qMemberEntity)
            .leftJoin(qBelongingEntity)
            .on(qBelongingEntity.belongingId.eq(qMemberEntity.belongingId))
            .where(
                qMemberEntity.nickname.like(q + "%")
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long totalCount = Optional.ofNullable(
                jpaQueryFactory.select(qMemberEntity.memberId.count())
                    .from(qMemberEntity)
                    .where(qMemberEntity.nickname.like(q + "%"))
                    .fetchFirst()
            )
            .orElse(0L);

        return new PageImpl<>(
            result,
            pageable,
            totalCount
        );
    }

    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        return memberJpaRepository.findById(memberId)
            .map(MemberEntity::toDomainModel);
    }

    @Override
    public void updateMember(Long memberId, Member updatingMember) {
        memberJpaRepository.findById(memberId)
            .orElseThrow(() -> new MemberException(ApplicationErrorType.MEMBER_NOT_FOUND));
    }

    @Override
    public MemberDetailResponseDto getMemberDetailByMemberId(Long memberId) {

        QBelongingEntity qBelongingEntity = new QBelongingEntity("belonging");

        return jpaQueryFactory.select(
            Projections.constructor(
                MemberDetailResponseDto.class,
                qMemberEntity.memberId,
                qMemberEntity.nickname,
                qMemberEntity.email,
                qMemberEntity.profileImgUrl,
                qMemberEntity.thirdPartyProfileUrl,
                qMemberEntity.shortIntroduction,
                qMemberEntity.belongingId,
                qBelongingEntity.name,
                qMemberEntity.myPage
            )
        )
            .from(qMemberEntity)
            .leftJoin(qBelongingEntity)
            .on(qBelongingEntity.belongingId.eq(qMemberEntity.belongingId))
            .where(qMemberEntity.memberId.eq(memberId))
            .fetchOne();
    }
}
