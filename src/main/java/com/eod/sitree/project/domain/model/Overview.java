package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.OverviewDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Overview {
    private final List<Image> images;
    private final ClientUrl clientUrl;
    private String detailDescription; // 상세 소개

    public Overview(OverviewDto dto) {
        this.images = dto.getImages().stream().map(Image::new).toList();
        this.clientUrl = new ClientUrl(dto.getClientUrl());
        this.detailDescription = dto.getDetailDescription();
    }
}
