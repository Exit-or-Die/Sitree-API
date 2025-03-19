package com.eod.sitree.member.domain.model;

import com.eod.sitree.belonging.domain.model.Belonging;
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
public class Career {

    private Long belongingId;

    private String belongingName;

    private String imageUrl;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startedAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endedAt;

    private String position;

    private String department;

    private List<Project> projects;

    public Career(Long belongingId, String belongingName, String imageUrl, LocalDateTime startedAt,
        LocalDateTime endedAt,
        String position, String department, List<Project> projects) {

        this.belongingId = belongingId;
        this.belongingName = belongingName;
        this.imageUrl = imageUrl;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.position = position;
        this.department = department;
        this.projects = projects;
    }

    public Career(Long belongingId, LocalDateTime startedAt, LocalDateTime endedAt,
        String position, String department, List<Project> projects) {

        this.belongingId = belongingId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.position = position;
        this.department = department;
        this.projects = projects;
    }

    public void updateBelonging(Belonging belonging) {

        if (belonging == null) {
            return;
        }

        this.belongingName = belonging.getName();
        this.imageUrl = belonging.getImageUrl();
    }

    public LocalDateTime findStartedAtOrElseNow() {

        if (this.startedAt == null) {

            return LocalDateTime.now();
        }

        return this.startedAt;
    }

    public LocalDateTime findEndedAtOrElseNow() {

        if (this.endedAt == null) {

            return LocalDateTime.now();
        }

        return this.endedAt;
    }
}