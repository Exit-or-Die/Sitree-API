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

    public Project(ProjectCreateRequestDto dto) {
        this.head = new Head(dto.getHeadDto());
        this.tags = dto.getTagDtoList().stream().map(Tag::new).toList();
        this.overview = new Overview(dto.getOverviewDto());
        this.techviews = dto.getTechviewDtoList().stream().map(Techview::new).toList();
        this.participants = dto.getParticipants().stream().map(Participant::new).toList();
    }

    public Project(
            ProjectEntity projectEntity,
            List<TagEntity> tagEntities,
            List<Techview> techviews,
            List<ParticipantEntity> participantEntities
    ) {
        this.head = new Head(projectEntity.getHeadEntity());
        this.tags = tagEntities.stream().map(Tag::new).toList();
        this.overview = new Overview(projectEntity.getOverviewEntity());
        this.techviews = techviews;
        this.participants = participantEntities.stream().map(Participant::new).toList();
    }
}
