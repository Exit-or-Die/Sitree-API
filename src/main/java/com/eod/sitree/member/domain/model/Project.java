package com.eod.sitree.member.domain.model;

import com.eod.sitree.member.domain.model.type.RoleTagType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Project {

    private String projectName;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startedAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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