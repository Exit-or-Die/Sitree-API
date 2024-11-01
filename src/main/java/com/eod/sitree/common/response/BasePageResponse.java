package com.eod.sitree.common.response;

import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BasePageResponse<T> {

    private List<T> content;

    private int page;

    private int size;

    private long total;

    private boolean hasPrev;

    private boolean hasNext;

    public BasePageResponse(Page page) {

        this.content = page.getContent();
        this.page = page.getPageable().getPageNumber();
        this.size = page.getPageable().getPageSize();
        this.total = page.getTotalElements();
        this.hasPrev = !page.isFirst();
        this.hasNext = !page.isLast();
    }
}
