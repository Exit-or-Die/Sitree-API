package com.eod.sitree.project.ui.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryGetResponseDto {

    private long categoryId;
    private String categoryName;
}
