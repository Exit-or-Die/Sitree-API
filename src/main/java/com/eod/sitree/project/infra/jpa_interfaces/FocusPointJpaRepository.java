package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.FocusPointEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FocusPointJpaRepository extends JpaRepository<FocusPointEntity, Long> {

    Optional<FocusPointEntity> findByFocusPointId(Long focusPointId);

    Optional<FocusPointEntity> findByParticipantId(Long participantId);

}
