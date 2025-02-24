package com.eod.sitree.member.domain.model;

import com.eod.sitree.member.domain.model.type.EducationActivityCategoryType;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EducationActivity {

    private String educationActivityName;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String majorOrOrganization;

    private EducationActivityCategoryType category;

    private String contents;

    public EducationActivity(String educationActivityName, LocalDateTime startedAt,
        LocalDateTime endedAt, String majorOrOrganization, EducationActivityCategoryType category,
        String contents) {
        this.educationActivityName = educationActivityName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.majorOrOrganization = majorOrOrganization;
        this.category = category;
        this.contents = contents;
    }
}