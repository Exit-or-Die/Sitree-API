package com.eod.sitree.belonging.infra;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingType;
import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import com.eod.sitree.belonging.infra.entity.BelongingEntity;
import com.eod.sitree.belonging.infra.entity.QBelongingEntity;
import com.eod.sitree.belonging.ui.dto.response.BelongingRankingResponseDto;
import com.eod.sitree.member.infra.entity.QMemberEntity;
import com.eod.sitree.project.infra.entity.QParticipantEntity;
import com.eod.sitree.project.infra.entity.QProjectEntity;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BelongingRepositoryImpl implements BelongingRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final BelongingJpaRepository belongingJpaRepository;

    private final QBelongingEntity belongingEntity = QBelongingEntity.belongingEntity;
    private final QProjectEntity projectEntity = QProjectEntity.projectEntity;
    private final QParticipantEntity participantEntity = QParticipantEntity.participantEntity;
    private final QMemberEntity memberEntity = QMemberEntity.memberEntity;

    private final static Long DEFAULT_RANKING_SIZE = 20L;


    @Override
    public List<Belonging> searchByName(String name) {

        return jpaQueryFactory.selectFrom(belongingEntity)
            .where(belongingEntity.name.like("%" + name + "%"))
            .fetch().stream()
            .map(BelongingEntity::toDomainModel).toList();
    }

    @Override
    public Optional<Belonging> findById(Long id) {

        return belongingJpaRepository.findById(id)
            .map(BelongingEntity::toDomainModel);
    }

    @Override
    public List<Belonging> findAll() {

        return belongingJpaRepository.findAll()
            .stream()
            .map(BelongingEntity::toDomainModel)
            .toList();
    }

    @Override
    public List<BelongingWithPoint> findBelongingGroupByProject() {

        return jpaQueryFactory.select(
                Projections.constructor(
                    BelongingWithPoint.class,
                    belongingEntity.belongingId,
                    belongingEntity.belongingType,
                    belongingEntity.name,
                    belongingEntity.imageUrl,
                    belongingEntity.currentRanking,
                    belongingEntity.prevRanking,
                    belongingEntity.belongingId.count()
                )
            )
            .from(projectEntity)
            .innerJoin(participantEntity)
            .on(
                participantEntity.projectId.eq(projectEntity.projectId)
            )
            .innerJoin(memberEntity)
            .on(
                memberEntity.memberId.eq(participantEntity.memberId)
            )
            .innerJoin(belongingEntity)
            .on(
                belongingEntity.belongingId.eq(memberEntity.belongingId)
            )
            .groupBy(
                belongingEntity.belongingId,
                belongingEntity.belongingType,
                belongingEntity.name,
                belongingEntity.imageUrl
            )
            .fetch();
    }

    @Override
    public List<BelongingEntity> saveAll(List<BelongingEntity> belongingEntities) {

        return belongingJpaRepository.saveAll(belongingEntities);
    }

    @Override
    public List<Belonging> findBelongingByRankingAsc(BelongingType belongingType) {

        return jpaQueryFactory.selectFrom(belongingEntity)
            .where(
                eqBelongingType(belongingType, belongingEntity)
            )
            .orderBy(
                orderNullsLast(belongingEntity.currentRanking, Order.ASC),
                belongingEntity.name.asc()
            )
            .limit(DEFAULT_RANKING_SIZE)
            .fetch()
            .stream()
            .map(BelongingEntity::toDomainModel)
            .toList();
    }

    @Override
    public List<BelongingRankingResponseDto> findBelongingByRankingAscWithProjectCount(
        BelongingType belongingType) {

        return jpaQueryFactory.select(
                Projections.constructor(
                    BelongingRankingResponseDto.class,
                    belongingEntity.belongingId,
                    belongingEntity.belongingType,
                    belongingEntity.name,
                    belongingEntity.imageUrl,
                    belongingEntity.currentRanking,
                    belongingEntity.prevRanking,
                    projectEntity.projectId.countDistinct()
                )
            )
            .from(belongingEntity)
            .leftJoin(memberEntity)
            .on(memberEntity.belongingId.eq(belongingEntity.belongingId))
            .leftJoin(participantEntity)
            .on(participantEntity.memberId.eq(memberEntity.memberId))
            .leftJoin(projectEntity)
            .on(projectEntity.projectId.eq(participantEntity.projectId))
            .where(
                eqBelongingType(belongingType, belongingEntity)
            )
            .groupBy(belongingEntity)
            .orderBy(
                orderNullsLast(belongingEntity.currentRanking, Order.ASC),
                belongingEntity.name.asc()
            )
            .limit(DEFAULT_RANKING_SIZE)
            .fetch();
    }

    @Override
    public Page<BelongingRankingResponseDto> findBelongingByRankingAscWithProjectCountAsPage(
        Pageable pageable, BelongingType belongingType) {

        List<BelongingRankingResponseDto> result = jpaQueryFactory.select(
                Projections.constructor(
                    BelongingRankingResponseDto.class,
                    belongingEntity.belongingId,
                    belongingEntity.belongingType,
                    belongingEntity.name,
                    belongingEntity.imageUrl,
                    belongingEntity.currentRanking,
                    belongingEntity.prevRanking,
                    projectEntity.projectId.countDistinct()
                )
            )
            .from(belongingEntity)
            .leftJoin(memberEntity)
            .on(memberEntity.belongingId.eq(belongingEntity.belongingId))
            .leftJoin(participantEntity)
            .on(participantEntity.memberId.eq(memberEntity.memberId))
            .leftJoin(projectEntity)
            .on(projectEntity.projectId.eq(participantEntity.projectId))
            .where(
                eqBelongingType(belongingType, belongingEntity)
            )
            .groupBy(belongingEntity)
            .orderBy(
                orderNullsLast(belongingEntity.currentRanking, Order.ASC),
                belongingEntity.name.asc()
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = Optional.ofNullable(
                jpaQueryFactory.select(belongingEntity.belongingId.count())
                    .from(belongingEntity)
                    .fetchOne()
            )
            .orElse(0L);

        return new PageImpl<>(result, pageable, total);
    }

    private <T extends Comparable> OrderSpecifier<T> orderNullsLast(Path<T> path, Order order) {
        return new OrderSpecifier<>(order, path, OrderSpecifier.NullHandling.NullsLast);
    }

    public BooleanExpression eqBelongingType(BelongingType belongingType,
        QBelongingEntity belongingEntity) {

        if (belongingType == null) {
            return null;
        }

        return belongingEntity.belongingType.eq(belongingType);
    }
}
