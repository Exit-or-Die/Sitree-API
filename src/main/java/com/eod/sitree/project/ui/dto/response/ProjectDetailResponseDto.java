package com.eod.sitree.project.ui.dto.response;

import com.eod.sitree.project.domain.model.Architecture;
import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.domain.model.Head;
import com.eod.sitree.project.domain.model.Image;
import com.eod.sitree.project.domain.model.Overview;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Techview;
import com.eod.sitree.project.domain.model.type.PlatformType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;

@Getter
public class ProjectDetailResponseDto {
    private final Head head;
    private final List<Category> categories;
    private final OverviewDto overview;
    private final List<Techview> techviewList;
    private final List<Architecture> architectureList;
    private final List<ParticipantDto> participantList;
    private final Long viewCount;
    private final LocalDateTime createdAt;
    private final boolean isHealthy;

    public ProjectDetailResponseDto(Project project, boolean isHealthy) {

        this.head = project.getHead();
        this.categories = project.getCategories();
        this.overview = new OverviewDto(project.getOverview());
        this.techviewList = project.getTechviews();
        this.architectureList = project.getArchitectures();
        this.participantList = project.getParticipants().stream().map(ParticipantDto::new).toList();
        this.viewCount = project.getViewCount();
        this.isHealthy = isHealthy;
        this.createdAt = project.getCreatedAt();
    }

    @Getter
    private static class OverviewDto{
        private final List<Image> images;
        private final HashMap<PlatformType, String> clientUrl;
        private final String detailDescription;

        public OverviewDto(Overview overview) {
            this.images = overview.getImages();
            this.clientUrl = overview.getClientUrl().getClientUrls();
            this.detailDescription = overview.getDetailDescription();
        }
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
