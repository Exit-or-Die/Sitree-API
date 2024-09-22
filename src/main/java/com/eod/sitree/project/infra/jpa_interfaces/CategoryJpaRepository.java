package com.eod.sitree.project.infra.jpa_interfaces;

import com.eod.sitree.project.infra.entity.CategoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findAllByNameIn(List<String> categoryNames);
}
