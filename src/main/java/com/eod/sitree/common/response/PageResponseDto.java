package com.eod.sitree.common.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto<T> {
    private int totalCount;
    private boolean isLast;
    private int page;
    private int size;
    private List<T> contests;
}
