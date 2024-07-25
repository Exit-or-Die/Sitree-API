package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.domain.model.type.TechStackType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Techview {
    private final String techArea; // 개발 영역
    private final String GitRepositoryUrl; // 깃 링크
    private List<TechStackType> techSTackTypes; // 사용 기술
    private Image architectureImage; // 아키텍쳐 이미지
    private String architectureDescription; // 아키텍쳐 구조 설명
    private List<String> focusedPoints; // 핵심 기술 내용
}
