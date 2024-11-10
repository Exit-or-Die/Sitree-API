package com.eod.sitree.project.ui.dto.response;

import com.eod.sitree.common.response.PageResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor
public class ProjectSearchResponseDto extends PageResponseDto {

    public ProjectSearchResponseDto(Page<ProjectSearchDisplayElement> pageResult) {
        super(
                pageResult.getNumberOfElements(),
                pageResult.isLast(),
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getContent()
        );
    }

    @Getter
    @AllArgsConstructor
    public static class ProjectSearchDisplayElement{
        private String thumbnailImageUrl;
        private String name;
        private String shortDescription;
    }

}
