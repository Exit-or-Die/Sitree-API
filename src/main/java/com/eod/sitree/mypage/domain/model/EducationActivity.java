package com.eod.sitree.mypage.domain.model;

import com.eod.sitree.mypage.domain.model.type.EducationActivityCategoryType;
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
}