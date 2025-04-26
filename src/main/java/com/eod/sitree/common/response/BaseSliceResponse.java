package com.eod.sitree.common.response;

import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

@Getter
public class BaseSliceResponse<T> {

    private List<T> content;

    private int page;

    private int size;

    private boolean hasPrev;

    private boolean hasNext;

    public BaseSliceResponse(Slice<T> slice) {
        this.content = slice.getContent();
        this.page = slice.getPageable().getPageNumber();
        this.size = slice.getPageable().getPageSize();
        this.hasPrev = slice.hasPrevious();
        this.hasNext = slice.hasNext();
    }
}
