package com.eod.sitree.project.ui.dto.request;

import com.eod.sitree.common.infra.validator.ValidEnum;
import com.eod.sitree.project.domain.model.ClientUrl;
import com.eod.sitree.project.domain.model.FocusPoint;
import com.eod.sitree.project.domain.model.Head;
import com.eod.sitree.project.domain.model.Image;
import com.eod.sitree.project.domain.model.Overview;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Tag;
import com.eod.sitree.project.domain.model.Techview;
import com.eod.sitree.project.domain.model.type.ImageType;
import com.eod.sitree.project.domain.model.type.PlatformType;
import com.eod.sitree.project.domain.model.type.TechStackType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectCreateRequestDto {
    @NotNull @Valid
    private HeadDto head;
    @NotNull @Valid
    private List<TagDto> tagList;
    @NotNull @Valid
    private OverviewDto overview;
    @Valid
    private List<TechviewDto> techviewList;
    @NotNull
    private List<ParticipantDto> participantList;

    public Project toDomainModel(){
        Head headDomainModel = this.head.toDomainModel();
        List<Tag> tagDomainModelList = this.tagList.stream().map(TagDto::toDomainModel).toList();
        Overview overviewDomainModel = this.overview.toDomainModel();
        List<Techview> techviewDomainModelList = techviewList.stream().map(TechviewDto::toDomainModel).toList();
        List<Participant> participantDomainModelList = participantList.stream().map(ParticipantDto::toDomainModel).toList();
        return new Project(headDomainModel, tagDomainModelList, overviewDomainModel,
                techviewDomainModelList, participantDomainModelList);
    }

    @Getter
    public static class HeadDto {
        @NotBlank
        private String thumbnailImageUrl;
        @NotBlank
        private String title;
        private String shortDescription;
        private String healthCheckUrl;

        private Head toDomainModel(){
            return new Head(this.thumbnailImageUrl, this.title, this.shortDescription,
                    this.healthCheckUrl);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class TagDto{
        @NotBlank
        private String name;

        private Tag toDomainModel(){
            return new Tag(this.name);
        }
    }

    @Getter
    public static class OverviewDto{
        @NotNull @Valid
        private List<ImageDto> images;
        @NotNull
        private ClientUrlDto clientUrl;
        @NotBlank
        private String detailDescription; // 상세 소개

        private Overview toDomainModel(){
            List<Image> imageDomainModelList = images.stream().map(ImageDto::toDomainModel).toList();
            ClientUrl clientUrlDomainModel = clientUrl.toDomainModel();
            return new Overview(imageDomainModelList, clientUrlDomainModel, this.detailDescription);
        }

        @Getter
        public static class ClientUrlDto{
            private String liveWebDomain;
            private HashMap<PlatformType, String> downloadMethods;

            private ClientUrl toDomainModel(){
                return new ClientUrl(this.liveWebDomain, this.downloadMethods);
            }
        }
    }

    @Getter
    public static class TechviewDto{
        @NotBlank
        private String techArea; // 개발 영역
        @NotBlank
        private String gitRepositoryUrl; // 깃 링크
        private List<TechStackType> techStackTypes; // 사용 기술
        @Valid
        private ImageDto architectureImage; // 아키텍쳐 이미지
        private String architectureDescription; // 아키텍쳐 구조 설명
        @Valid
        private List<FocusPointDto> focusedPoints; // 핵심 기술 내용

        private Techview toDomainModel(){
            Image imageDomainModel = architectureImage.toDomainModel();
            List<FocusPoint> focusPointDomainModelList = this.focusedPoints.stream()
                    .map(FocusPointDto::toDomainModel).toList();
            return new Techview(this.techArea, this.gitRepositoryUrl, this.techStackTypes,
                    imageDomainModel, this.architectureDescription, focusPointDomainModelList);
        }

        @Getter
        public static class FocusPointDto{
            @NotNull
            private Long memberId;
            @NotBlank
            private String focusedOn;

            private FocusPoint toDomainModel(){
                return new FocusPoint(this.memberId, this.focusedOn);
            }
        }
    }

    @Getter
    public static class ImageDto{
        @NotBlank
        private String imageUrl;
        @ValidEnum(enumClass = ImageType.class)
        private ImageType imageType;

        public Image toDomainModel(){
            return new Image(imageUrl, imageType);
        }
    }

    @Getter
    public static class ParticipantDto{
        @NotNull
        private Long memberId;
        @NotBlank
        private String position;
        @NotNull
        private Boolean isLeader;

        private Participant toDomainModel(){
            return new Participant(this.memberId, this.position, this.isLeader);
        }
    }
}
