package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.infra.entity.TagEntity;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.TagDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Tag {
    private final String name;

    public Tag(TagDto dto) {
        this.name = dto.getName();
    }

    public Tag(TagEntity entity) {
        this.name = entity.getName();
    }
}
