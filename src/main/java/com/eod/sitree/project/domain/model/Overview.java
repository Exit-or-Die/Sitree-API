package com.eod.sitree.project.domain.model;

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
    private final List<Long> participants; // 참여자
    private String foreword; // 머릿말
    private String manual; // 사용 방법
    private String detailDescription; // 상세 소개
}
