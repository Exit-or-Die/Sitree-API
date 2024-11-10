package com.eod.sitree.project.ui.dto.request;

import com.eod.sitree.common.request.PageRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectSearchRequestDto extends PageRequestDto {

    private String name;
}
