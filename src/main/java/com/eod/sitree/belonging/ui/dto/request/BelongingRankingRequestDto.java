package com.eod.sitree.belonging.ui.dto.request;

import com.eod.sitree.belonging.domain.model.BelongingType;
import com.eod.sitree.common.request.BasePageRequest;


public class BelongingRankingRequestDto extends BasePageRequest {

    private String type;

    public BelongingRankingRequestDto(String type, int page, int size) {
        super(page, size);
        this.type = type;
    }

    public BelongingType getBelongingType() {

        if(BelongingType.exist(this.type)) {

            return BelongingType.valueOf(type);
        }

        return null;
    }
}
