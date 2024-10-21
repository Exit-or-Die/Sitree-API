package com.eod.sitree.belonging.domain.model;

import lombok.Getter;

@Getter
public class BelongingWithPoint extends Belonging{

    private long point;

    public BelongingWithPoint(Belonging belonging) {

        super(
            belonging.getBelongingId(),
            belonging.getType(),
            belonging.getName(),
            belonging.getImageUrl()
        );
        this.point = 0L;
    }

    public void addPointByBelongingCount(long belongingCount) {

        if (belongingCount <= 0) {
            return;
        }

        this.point += 10 + (belongingCount - 1) * 2;
    }
}
