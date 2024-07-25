package com.eod.sitree.project.infra;

import com.eod.sitree.project.infra.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {
}
