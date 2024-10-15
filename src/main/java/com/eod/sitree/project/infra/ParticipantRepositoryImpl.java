package com.eod.sitree.project.infra;

import static com.eod.sitree.project.infra.entity.QFocusPointEntity.focusPointEntity;
import static com.eod.sitree.project.infra.entity.QParticipantEntity.participantEntity;

import com.eod.sitree.project.domain.model.FocusPoint;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.modelRepository.ParticipantRepository;
import com.eod.sitree.project.infra.entity.ParticipantEntity;
import com.eod.sitree.project.infra.jpa_interfaces.FocusPointJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ParticipantJpaRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final ParticipantJpaRepository participantJpaRepository;
    private final FocusPointJpaRepository focusPointJpaRepository;

    @Override
    public List<Participant> findAllByProjectId(long projectId) {
        return jpaQueryFactory.select(Projections.constructor(Participant.class,
                                participantEntity.memberId,
                                participantEntity.position,
                                participantEntity.isLeader,
                                Projections.constructor(FocusPoint.class,
                                        focusPointEntity.memberId,
                                        focusPointEntity.focusedOn
                                )
                        )
                )
                .from(participantEntity)
                .leftJoin(focusPointEntity).on(participantEntity.participantId.eq(focusPointEntity.participantId))
                .where(participantEntity.projectId.eq(projectId))
                .fetch();
    }

    @Override
    public void saveAll(List<Participant> participants, long projectId) {
        List<ParticipantEntity> participantEntityList = participants.stream()
                .map(p -> new ParticipantEntity(projectId, p)).toList();
        participantJpaRepository.saveAll(participantEntityList);
    }


}
