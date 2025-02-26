package com.eod.sitree.project.infra;

import static com.eod.sitree.project.infra.entity.QFocusPointEntity.focusPointEntity;
import static com.eod.sitree.project.infra.entity.QParticipantEntity.participantEntity;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.model.FocusPoint;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.modelRepository.ParticipantRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.entity.ParticipantEntity;
import com.eod.sitree.project.infra.jpa_interfaces.FocusPointJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ParticipantJpaRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
                                        focusPointEntity.focusPoints
                                )
                        )
                )
                .from(participantEntity)
                .leftJoin(focusPointEntity)
                .on(participantEntity.participantId.eq(focusPointEntity.participantId))
                .where(participantEntity.projectId.eq(projectId))
                .fetch();
    }

    @Override
    public void saveAll(List<Participant> participants, long projectId) {
        List<ParticipantEntity> participantEntityList = participants.stream()
                .map(p -> new ParticipantEntity(projectId, p)).toList();
        participantJpaRepository.saveAll(participantEntityList);
    }

    @Override
    public void updateParticipants(long projectId, List<Participant> updateParticipants) {
        // 업데이트 참여자
        Map<Long, Participant> updateParticipantMap = updateParticipants.stream().collect(
                Collectors.toMap(Participant::getMemberId, p -> p));

        // 기존 참여자
        List<ParticipantEntity> participantEntities = participantJpaRepository.findAllByProjectId(
                projectId);

        List<ParticipantEntity> updateParticipantEntityList = new ArrayList<>();
        List<ParticipantEntity> deleteParticipantEntityList = new ArrayList<>();

        // 기존 참여자 중 Update 대상 여부에 따라 처리
        participantEntities.forEach(p -> {
            if (updateParticipantMap.containsKey(p.getMemberId())) {
                p.updateParticipantEntity(updateParticipantMap.get(p.getMemberId()));
                updateParticipantMap.remove(p.getMemberId());
                updateParticipantEntityList.add(p);
            } else {
                deleteParticipantEntityList.add(p);
            }
        });

        // 신규 참여자 추가
        updateParticipantMap.values().forEach(p -> {
            ParticipantEntity participantEntity = new ParticipantEntity(projectId, p);
            updateParticipantEntityList.add(participantEntity);
        });

        if (!deleteParticipantEntityList.isEmpty()) {
            participantJpaRepository.deleteAll(deleteParticipantEntityList);
        }
        if (!updateParticipantEntityList.isEmpty()) {
            participantJpaRepository.saveAll(updateParticipantEntityList);
        }
    }

    @Override
    public Long getParticipantId(Long memberId, Long projectId) {
        ParticipantEntity participantEntity = participantJpaRepository.findByProjectIdAndMemberId(
                        projectId, memberId).orElseThrow(
                        () -> new ProjectException(ApplicationErrorType.NOT_EXIST_PARTICIPANT));
        return participantEntity.getParticipantId();
    }

}
