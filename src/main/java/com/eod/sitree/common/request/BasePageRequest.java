package com.eod.sitree.common.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor
public class BasePageRequest {

    private Integer pageNo;

    private Integer size;

    private static final int DEFAULT_PAGE = 1;

    private static final int MIN_PAGE = 1;

    private static final int DEFAULT_SIZE = 10;

    private static final int MIN_SIZE = 10;


    public BasePageRequest(Integer pageNo, Integer size) {

        this.pageNo = pageNo == null || pageNo < MIN_PAGE ? DEFAULT_PAGE : pageNo;
        this.size = size == null || size < MIN_SIZE ? DEFAULT_SIZE : size;
    }

    public Pageable getPageable() {

        return PageRequest.of(Math.max(pageNo - 1, 0), size);
    }
}
