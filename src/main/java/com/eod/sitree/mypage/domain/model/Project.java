package com.eod.sitree.mypage.domain.model;

import com.eod.sitree.mypage.domain.model.type.RoleTagType;
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
}