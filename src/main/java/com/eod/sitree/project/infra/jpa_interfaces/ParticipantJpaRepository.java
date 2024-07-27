package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantJpaRepository extends JpaRepository<ParticipantEntity, Long> {

}
