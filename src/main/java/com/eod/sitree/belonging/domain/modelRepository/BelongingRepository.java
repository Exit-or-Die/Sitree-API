package com.eod.sitree.belonging.domain.modelRepository;

import com.eod.sitree.belonging.domain.model.Belonging;
import java.util.List;

public interface BelongingRepository {

    List<Belonging> searchByName(String name);
}
