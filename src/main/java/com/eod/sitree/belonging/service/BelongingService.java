package com.eod.sitree.belonging.service;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import com.eod.sitree.belonging.infra.entity.BelongingEntity;
import com.eod.sitree.belonging.ui.dto.request.BelongingRankingRequestDto;
import com.eod.sitree.belonging.ui.dto.response.BelongingRankingResponseDto;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BelongingService {

    private final BelongingRepository belongingRepository;

    public List<Belonging> searchByName(String name) {

        return belongingRepository.searchByName(name);
    }

    public List<BelongingRankingResponseDto> findRanking(BelongingRankingRequestDto request) {

        return belongingRepository.findBelongingByRankingAscWithProjectCount(request.getBelongingType());
    }
}
