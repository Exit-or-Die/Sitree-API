package com.eod.sitree.mypage.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class Career {

    private String careerName;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String position;

    private String department;

    private List<Project> projects;
}