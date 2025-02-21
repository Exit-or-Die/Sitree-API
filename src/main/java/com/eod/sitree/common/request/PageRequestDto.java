package com.eod.sitree.common.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
public class PageRequestDto {
    private int pageNo = 0;
    private int size = 10;

    public Pageable getPageableParam() {
        return PageRequest.of(this.pageNo, this.size);
    }
}
