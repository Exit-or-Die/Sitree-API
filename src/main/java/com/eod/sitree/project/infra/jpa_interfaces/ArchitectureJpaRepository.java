package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.ArchitectureEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchitectureJpaRepository extends JpaRepository<ArchitectureEntity, Long> {

    List<ArchitectureEntity> findAllByProjectId(long projectId);
}
