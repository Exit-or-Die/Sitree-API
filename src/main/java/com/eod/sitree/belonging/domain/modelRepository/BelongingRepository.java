package com.eod.sitree.belonging.domain.modelRepository;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.belonging.infra.entity.BelongingEntity;
import java.util.List;
import java.util.Optional;

public interface BelongingRepository {

    List<Belonging> searchByName(String name);

    Optional<Belonging> findById(Long id);

    List<Belonging> findAll();

    List<BelongingWithPoint> findBelongingGroupByProject();

    List<BelongingEntity> saveAll(List<BelongingEntity> belongingEntities);

    List<Belonging> findBelongingByRankingAsc();
}
