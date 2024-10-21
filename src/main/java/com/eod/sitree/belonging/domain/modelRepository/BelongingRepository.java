package com.eod.sitree.belonging.domain.modelRepository;

import com.eod.sitree.belonging.domain.model.Belonging;
import java.util.List;
import java.util.Optional;

public interface BelongingRepository {

    List<Belonging> searchByName(String name);

    Optional<Belonging> findById(Long id);

    List<Belonging> findAll();
}
