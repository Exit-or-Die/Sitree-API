package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.TagEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findAllByProjectId(long projectId);
}
