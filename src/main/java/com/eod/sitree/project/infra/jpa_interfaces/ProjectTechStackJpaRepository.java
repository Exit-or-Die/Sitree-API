package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.ProjectTechStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTechStackJpaRepository extends JpaRepository<ProjectTechStackEntity, Long> {

}
