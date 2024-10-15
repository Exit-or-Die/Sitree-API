package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.ProjectLikesEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectLikesRepository extends JpaRepository<ProjectLikesEntity, Long> {

    Optional<ProjectLikesEntity> findByProjectIdAndMemberId(long projectId, long memberId);
}
