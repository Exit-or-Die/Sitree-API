package com.eod.sitree.common.request;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class BasePageRequest {

    private int page;

    private int size;

    private static final int DEFAULT_PAGE = 1;

    private static final int MIN_PAGE = 1;

    private static final int DEFAULT_SIZE = 10;

    private static final int MIN_SIZE = 10;


    public BasePageRequest(Integer page, Integer size) {

        this.page = page == null || page < MIN_PAGE ? DEFAULT_PAGE : page;
        this.size = size == null || size < MIN_SIZE ? DEFAULT_SIZE : size;
    }

    public Pageable getPageable() {

        return PageRequest.of(Math.max(page - 1, 0), size);
    }
}
