package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.infra.entity.ParticipantEntity;
import com.eod.sitree.project.infra.entity.ProjectEntity;
import com.eod.sitree.project.infra.entity.TagEntity;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Project {
    private final Head head;
    private final List<Tag> tags;
    private final Overview overview;
    private final List<Techview> techviews;
    private final List<Participant> participants;
    private final Long viewCount;

    public Project(ProjectCreateRequestDto dto) {
        this.head = new Head(dto.getHead());
        this.tags = dto.getTagList().stream().map(Tag::new).toList();
        this.overview = new Overview(dto.getOverview());
        this.techviews = dto.getTechviewList().stream().map(Techview::new).toList();
        this.participants = dto.getParticipantList().stream().map(Participant::new).toList();
        this.viewCount = 0L;
    }

    public String getHealthCheckUrl() {
        return this.head.getHealthCheckUrl();
    }
}
