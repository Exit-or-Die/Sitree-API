package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.CategoryUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryUsageJpaRepository extends JpaRepository<CategoryUsageEntity, Long> {

}
