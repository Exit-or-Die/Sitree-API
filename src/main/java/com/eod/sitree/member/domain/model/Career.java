package com.eod.sitree.member.domain.model;

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

    public Career(String careerName, LocalDateTime startedAt, LocalDateTime endedAt,
        String position,
        String department, List<Project> projects) {
        this.careerName = careerName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.position = position;
        this.department = department;
        this.projects = projects;
    }
}