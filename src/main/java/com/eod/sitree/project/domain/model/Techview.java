package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.domain.model.type.TechStackType;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.TechviewDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Techview {
    private final String techArea; // 개발 영역
    private final String gitRepositoryUrl; // 깃 링크
    private List<TechStackType> techStackTypes; // 사용 기술
    private Image architectureImage; // 아키텍쳐 이미지
    private String architectureDescription; // 아키텍쳐 구조 설명
    private List<FocusPoint> focusedPoints; // 핵심 기술 내용

    public Techview(TechviewDto dto) {
        this.techArea = dto.getTechArea();
        this.gitRepositoryUrl = dto.getGitRepositoryUrl();
        this.techStackTypes = dto.getTechStackTypes();
        this.architectureImage = new Image(dto.getArchitectureImage());
        this.architectureDescription = dto.getArchitectureDescription();
        this.focusedPoints = dto.getFocusedPoints().stream().map(FocusPoint::new).toList();
    }
}
