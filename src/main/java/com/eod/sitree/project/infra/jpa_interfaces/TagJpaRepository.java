package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {

}
