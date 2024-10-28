package com.eod.sitree.belonging.infra;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import com.eod.sitree.belonging.infra.entity.BelongingEntity;
import com.eod.sitree.belonging.infra.entity.QBelongingEntity;
import com.eod.sitree.member.infra.entity.QMemberEntity;
import com.eod.sitree.project.infra.entity.QParticipantEntity;
import com.eod.sitree.project.infra.entity.QProjectEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
}
