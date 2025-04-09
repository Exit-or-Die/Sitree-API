package com.eod.sitree.belonging.ui.dto.response;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.common.response.BasePageResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BelongingSearchPageResponseDto extends BasePageResponse<Belonging> {

    public BelongingSearchPageResponseDto(Page page) {

        super(page);
    }
}
