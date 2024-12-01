package com.eod.sitree.belonging.domain.model;

import lombok.Getter;

@Getter
public class Belonging {

    private final Long belongingId;

    private final BelongingType type;

    private final String name;

    private final String imageUrl;

    private final Long currentRanking;

    private final Long prevRanking;


    public Belonging(Long belongingId, BelongingType type, String name, String imageUrl,
        Long currentRanking, Long prevRanking) {
        this.belongingId = belongingId;
        this.type = type;
        this.name = name;
        this.imageUrl = imageUrl;
        this.currentRanking = currentRanking;
        this.prevRanking = prevRanking;
    }
}
