package com.eod.sitree.member.domain.model;

import com.eod.sitree.member.domain.model.type.EducationActivityCategoryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EducationActivity {

    private String educationActivityName;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startedAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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