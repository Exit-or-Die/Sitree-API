package com.eod.sitree.common.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseTimeDomain {

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
