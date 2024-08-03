package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.TechviewEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechviewJpaRepository extends JpaRepository<TechviewEntity, Long> {

    List<TechviewEntity> findAllByProjectId(long projectId);
}
