package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.ParticipantEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantJpaRepository extends JpaRepository<ParticipantEntity, Long> {

    List<ParticipantEntity> findAllByProjectId(long projectId);

    Optional<ParticipantEntity> findByProjectIdAndMemberId(long projectId, long memberId);
}
