package com.eod.sitree.project.ui.dto.response;

import com.eod.sitree.project.domain.model.Head;
import com.eod.sitree.project.domain.model.Overview;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Tag;
import com.eod.sitree.project.domain.model.Techview;
import java.util.List;
import lombok.Getter;

@Getter
public class ProjectDetailResponseDto {
    private final Head head;
    private final List<Tag> tagList;
    private final Overview overview;
    private final List<Techview> techviewList;
    private final List<Participant> participantList;
    private final Long viewCount;

    public ProjectDetailResponseDto(Project project) {
        this.head = project.getHead();
        this.tagList = project.getTags();
        this.overview = project.getOverview();
        this.techviewList = project.getTechviews();
        this.participantList = project.getParticipants();
        this.viewCount = project.getViewCount();
    }
}
