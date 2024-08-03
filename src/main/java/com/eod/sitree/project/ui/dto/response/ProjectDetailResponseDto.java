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

    public ProjectDetailResponseDto(Project project) {
        this.head = project.getHead();
        this.tagList = project.getTags();
        this.overview = project.getOverview();
        this.techviewList = project.getTechviews();
        this.participantList = project.getParticipants();
    }

//    private static class HeadDto {
//        private String thumbnailImageUrl;
//        private String title;
//        private String shortDescription;
//        private String healthCheckUrl;
//
//        public HeadDto(Head head) {
//            this.thumbnailImageUrl = head.getThumbnailImageUrl();
//            this.title = head.getTitle();
//            this.shortDescription = head.getShortDescription();
//            this.healthCheckUrl = head.getHealthCheckUrl();
//        }
//    }
//
//    private static class TagDto{
//        private String name;
//    }
//
//    private static class OverviewDto{
//        private List<ImageDto> imageList;
//        private ClientUrlDto clientUrl;
//        private String detailDescription; // 상세 소개
//
//        private static class ClientUrlDto{
//            private String liveWebDomain;
//            private HashMap<PlatformType, String> downloadMethods;
//        }
//    }
//
//    private static class TechviewDto{
//        private String techArea; // 개발 영역
//        private String gitRepositoryUrl; // 깃 링크
//        private List<TechStackType> techStackTypeList; // 사용 기술
//        private ImageDto architectureImage; // 아키텍쳐 이미지
//        private String architectureDescription; // 아키텍쳐 구조 설명
//        private List<FocusPointDto> focusedPointList; // 핵심 기술 내용
//
//        private static class FocusPointDto{
//            private Long memberNo;
//            private String focusedOn;
//        }
//    }
//
//    private static class ImageDto{
//        private String imageUrl;
//        private ImageType imageType;
//    }
//
//    private static class ParticipantDto{
//        private Long memberNo;
//        private String position;
//    }
}
