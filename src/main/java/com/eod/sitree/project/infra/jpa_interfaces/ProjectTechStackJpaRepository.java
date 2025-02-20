package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.ProjectTechStackEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTechStackJpaRepository extends JpaRepository<ProjectTechStackEntity, Long> {

    List<ProjectTechStackEntity> findAllByTechviewId(long techviewId);

    List<ProjectTechStackEntity> findAllByTechviewIdIn(List<Long> techviewIds);

    void deleteByTechviewIdIn(List<Long> techviewIds);

    void deleteAllByTechviewId (long techview);
}
