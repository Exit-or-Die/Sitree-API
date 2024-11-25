package com.eod.sitree.belonging.support;

import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import com.eod.sitree.belonging.infra.entity.BelongingEntity;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BelongingRankingScheduler {

    private final BelongingRepository belongingRepository;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 00시
    public void calculateRanking() {

        List<BelongingWithPoint> belongingGroupByProject = belongingRepository.findBelongingGroupByProject();
        AtomicLong index = new AtomicLong(1L);

        List<BelongingEntity> belongingEntities = belongingGroupByProject.stream()
            .map(BelongingWithPoint::calculatePointAndReturn)
            .sorted(Comparator.comparing(BelongingWithPoint::getPoint).reversed())
            .map(BelongingEntity::new)
            .map(belongingEntity -> belongingEntity.updateRankingAndReturn(index.getAndIncrement()))
            .toList();

        belongingRepository.saveAll(belongingEntities);
    }
}


