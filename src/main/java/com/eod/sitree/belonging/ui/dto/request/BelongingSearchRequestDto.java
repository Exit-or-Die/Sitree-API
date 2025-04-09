package com.eod.sitree.belonging.ui.dto.request;

import com.eod.sitree.common.request.BasePageRequest;

public class BelongingSearchRequestDto extends BasePageRequest {

    private String name;

    public BelongingSearchRequestDto(String name, Integer pageNo, Integer size) {
        super(pageNo, size == null ? 30 : size);
        this.name = name;
    }

    public String getName() {
        return name == null ? "" : name;
    }
}
