package com.eod.sitree.belonging.service;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BelongingService {

    private final BelongingRepository belongingRepository;

    //TODO: 소속 검색.
    public List<Belonging> searchByName(String name) {

        return belongingRepository.searchByName(name);
    }
}
