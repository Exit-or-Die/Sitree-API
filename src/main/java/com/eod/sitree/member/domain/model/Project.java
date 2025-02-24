package com.eod.sitree.member.domain.model;

import com.eod.sitree.member.domain.model.type.RoleTagType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class Project {

    private String projectName;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String contents;

    private List<RoleTagType> roleTags;

    public Project(String projectName, LocalDateTime startedAt, LocalDateTime endedAt,
        String contents,
        List<RoleTagType> roleTags) {
        this.projectName = projectName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.contents = contents;
        this.roleTags = roleTags;
    }
}