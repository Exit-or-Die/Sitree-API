package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.FocusPointEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FocusPointJpaRepository extends JpaRepository<FocusPointEntity, Long> {

    List<FocusPointEntity> findAllByTechviewId(long techviewId);
}
