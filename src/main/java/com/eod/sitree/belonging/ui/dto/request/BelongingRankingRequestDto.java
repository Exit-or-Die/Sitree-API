package com.eod.sitree.belonging.ui.dto.request;

import com.eod.sitree.belonging.domain.model.BelongingType;


public class BelongingRankingRequestDto {

    private String type;

    public BelongingRankingRequestDto(String type) {
        this.type = type;
    }

    public BelongingType getBelongingType() {

        if(BelongingType.exist(this.type)) {

            return BelongingType.valueOf(type);
        }

        return null;
    }
}
