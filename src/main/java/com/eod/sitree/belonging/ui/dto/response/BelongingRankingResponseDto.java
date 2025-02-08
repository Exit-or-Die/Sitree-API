package com.eod.sitree.belonging.ui.dto.response;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingType;
import com.eod.sitree.common.response.BasePageResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BelongingRankingResponseDto  {

    private final Long belongingId;

    private final BelongingType type;

    private final String name;

    private final String imageUrl;

    private final Long currentRanking;

    private final Long prevRanking;

    private final Long projectCount;

    public BelongingRankingResponseDto(Long belongingId, BelongingType type, String name,
        String imageUrl, Long currentRanking, Long prevRanking, Long projectCount) {
        this.belongingId = belongingId;
        this.type = type;
        this.name = name;
        this.imageUrl = imageUrl;
        this.currentRanking = currentRanking;
        this.prevRanking = prevRanking;
        this.projectCount = projectCount;
    }
}
