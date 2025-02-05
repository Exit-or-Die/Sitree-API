package com.eod.sitree.belonging.ui.dto.response;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.common.response.BasePageResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BelongingRankingResponseDto extends BasePageResponse<Belonging> {

    public BelongingRankingResponseDto(Page page) {
        super(page);
    }
}
