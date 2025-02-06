package com.eod.sitree.project.ui.dto.response;

import com.eod.sitree.project.domain.model.Architecture;
import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.domain.model.Head;
import com.eod.sitree.project.domain.model.Image;
import com.eod.sitree.project.domain.model.Techview;
import com.eod.sitree.project.domain.model.type.PlatformType;
import com.eod.sitree.project.infra.entity.OverviewEntity;
import com.eod.sitree.project.infra.entity.ProjectEntity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ProjectDetailResponseDto {

    private final Head head;
    private final List<Category> categories;
    private final OverviewDto overview;
    private final List<Techview> techviewList;
    private final List<Architecture> architectureList;
    private final List<ParticipantDto> participantList;
    private final Long viewCount;
    private Long likeCount;
    private Boolean isLiked;
    private final LocalDateTime createdAt;
    private boolean isHealthy;

//    public ProjectDetailResponseDto(Project project, Long likeCount, boolean isLiked,
//            boolean isHealthy) {
//
//        this.head = project.getHead();
//        this.categories = project.getCategories();
//        this.overview = new OverviewDto(project.getOverview());
//        this.techviewList = project.getTechviews();
//        this.architectureList = project.getArchitectures();
//        this.participantList = project.getParticipants().stream().map(ParticipantDto::new).toList();
//        this.viewCount = project.getViewCount();
//        this.isHealthy = isHealthy;
//        this.createdAt = project.getCreatedAt();
//        this.likeCount = likeCount;
//        this.isLiked = isLiked;
//    }

    public ProjectDetailResponseDto(ProjectDetailDto project, List<Category> categories, List<Techview> techviews,
            List<Architecture> architectures, List<ParticipantDto> participants) {

        this.head = project.getHead();
        this.categories = categories;
        this.overview = project.getOverview();
        this.techviewList = techviews;
        this.architectureList = architectures;
        this.participantList = participants;
        this.viewCount = project.getViewCount();
        this.createdAt = project.getCreatedAt();
    }

    public void setLikeInfo(long likeCount, boolean isLiked) {
        this.likeCount = likeCount;
        this.isLiked = isLiked;
    }

    public void setHealthCheck(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }

    @Getter
    public static class ProjectDetailDto{
        private final Head head;
        private final OverviewDto overview;
        private final Long viewCount;
        private final LocalDateTime createdAt;

        public ProjectDetailDto(Head head, OverviewDto overview, Long viewCount,
                LocalDateTime createdAt) {
            this.head = head;
            this.overview = overview;
            this.viewCount = viewCount;
            this.createdAt = createdAt;
        }
    }

    @Getter
    public static class OverviewDto {

        private final List<Image> images;
        private final HashMap<PlatformType, String> clientUrl;
        private final String detailDescription;

        public OverviewDto(OverviewEntity entity) {
            this.images = entity.toDomainModel().getImages();
            this.clientUrl = entity.getClientUrl().getClientUrls();
            this.detailDescription = entity.getDetailDescription();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class ParticipantDto {

        private final Long memberId;
        private final String nickname;
        private final String imageUrl;
        private final String position;
        private final boolean isLeader;
        private final String focusPoint;
    }
}
