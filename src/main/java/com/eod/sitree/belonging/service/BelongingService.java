package com.eod.sitree.belonging.service;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BelongingService {

    private final BelongingRepository belongingRepository;

    public List<Belonging> searchByName(String name) {

        return belongingRepository.searchByName(name);
    }

    public List<BelongingWithPoint> calculateBelongingPoints() {

        List<BelongingWithPoint> belongingGroupByProject = belongingRepository.findBelongingGroupByProject();

        return belongingGroupByProject.stream()
            .map(BelongingWithPoint::calculatePointAndReturn)
            .sorted(Comparator.comparing(BelongingWithPoint::getPoint).reversed())
            .toList();
    }
}
