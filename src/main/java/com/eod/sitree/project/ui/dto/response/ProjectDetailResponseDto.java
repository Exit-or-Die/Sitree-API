package com.eod.sitree.project.ui.dto.response;

import com.eod.sitree.project.domain.model.Architecture;
import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.domain.model.Head;
import com.eod.sitree.project.domain.model.Overview;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Techview;
import java.util.List;
import lombok.Getter;

@Getter
public class ProjectDetailResponseDto {
    private final Head head;
    private final List<Category> categories;
    private final Overview overview;
    private final List<Techview> techviewList;
    private final List<Architecture> architectureList;
    private final List<ParticipantDto> participantList;
    private final Long viewCount;
    private final boolean isHealthy;

    public ProjectDetailResponseDto(Project project, boolean isHealthy) {

        this.head = project.getHead();
        this.categories = project.getCategories();
        this.overview = project.getOverview();
        this.techviewList = project.getTechviews();
        this.architectureList = project.getArchitectures();
        this.participantList = project.getParticipants().stream().map(ParticipantDto::new).toList();
        this.viewCount = project.getViewCount();
        this.isHealthy = isHealthy;
    }

    @Getter
    private static class ParticipantDto {
        private final Long memberId;
        private final String position;
        private final boolean isLeader;
        private final String focusPoint;

        public ParticipantDto(Participant participant) {
            this.memberId= participant.getMemberId();
            this.position = participant.getPosition();
            this.isLeader = participant.isLeader();
            this.focusPoint = participant.getFocusPoint().getFocusedOn();
        }
    }
}
