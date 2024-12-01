package com.eod.sitree.belonging.domain.model;

import lombok.Getter;

@Getter
public class BelongingWithPoint extends Belonging{

    private long count;
    private long point;

    private static final long BASE_POINT = 10L;
    private static final long ADDITIONAL_POINT_WEIGHT = 2L;


    public BelongingWithPoint(Long belongingId, BelongingType type, String name, String imageUrl,
        Long currentRanking, Long prevRanking, long count) {

        super(belongingId, type, name, imageUrl, currentRanking, prevRanking);
        this.count = count;
        this.point = 0L;
    }

    public void calculatePointByBelongingCount() {

        if (this.count <= 0) {
            return;
        }

        this.point += BASE_POINT + (this.count - 1) * ADDITIONAL_POINT_WEIGHT;
    }

    public BelongingWithPoint calculatePointAndReturn() {

        calculatePointByBelongingCount();

        return this;
    }
}
