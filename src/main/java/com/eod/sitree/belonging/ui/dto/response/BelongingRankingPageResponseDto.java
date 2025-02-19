package com.eod.sitree.belonging.ui.dto.response;

import com.eod.sitree.common.response.BasePageResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BelongingRankingPageResponseDto extends BasePageResponse<BelongingRankingResponseDto> {

    public BelongingRankingPageResponseDto(Page page) {

        super(page);
    }
}
