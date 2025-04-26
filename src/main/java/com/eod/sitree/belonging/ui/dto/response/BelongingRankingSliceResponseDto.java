package com.eod.sitree.belonging.ui.dto.response;

import com.eod.sitree.common.response.BasePageResponse;
import com.eod.sitree.common.response.BaseSliceResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

@Getter
public class BelongingRankingSliceResponseDto extends BaseSliceResponse<BelongingRankingResponseDto> {

    public BelongingRankingSliceResponseDto(Slice slice) {

        super(slice);
    }
}
