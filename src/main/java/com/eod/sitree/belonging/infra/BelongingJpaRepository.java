package com.eod.sitree.belonging.infra;

import com.eod.sitree.belonging.infra.entity.BelongingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BelongingJpaRepository extends JpaRepository<BelongingEntity, Long> {

}
