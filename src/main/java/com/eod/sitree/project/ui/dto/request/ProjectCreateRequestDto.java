package com.eod.sitree.project.ui.dto.request;

import com.eod.sitree.common.infra.validator.ValidEnum;
import com.eod.sitree.project.domain.model.Architecture;
import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.domain.model.ClientUrl;
import com.eod.sitree.project.domain.model.Head;
import com.eod.sitree.project.domain.model.Image;
import com.eod.sitree.project.domain.model.Overview;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Techview;
import com.eod.sitree.project.domain.model.type.ArchitectureType;
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
    private List<ProjectTagDto> categories; // Domain Model 에서는 Category 로 사용됨
    @NotNull @Valid
    private OverviewDto overview;
    @Valid
    private List<TechviewDto> techviewList;
    private List<ArchitectureDto> architectureList;
    @NotNull
    private List<ParticipantDto> participantList;

    public Project toDomainModel(){
        Head headDomainModel = this.head.toDomainModel();
        List<Category> categoryDomainModelList = this.categories.stream().map(ProjectTagDto::toDomainModel).toList();
        Overview overviewDomainModel = this.overview.toDomainModel();
        List<Techview> techviewDomainModelList = techviewList.stream().map(TechviewDto::toDomainModel).toList();
        List<Architecture> architectureDomainModelList = architectureList.stream().map(ArchitectureDto::toDomainModel).toList();
        List<Participant> participantDomainModelList = participantList.stream().map(ParticipantDto::toDomainModel).toList();
        return new Project(headDomainModel, categoryDomainModelList, overviewDomainModel,
                techviewDomainModelList,architectureDomainModelList, participantDomainModelList, null);
    }

    @Getter
    public static class HeadDto {
        @NotBlank
        private String thumbnailImageUrl;
        @NotBlank
        private String title;
        @NotBlank
        private String shortDescription;
        private String healthCheckUrl;

        private Head toDomainModel(){
            return new Head(this.thumbnailImageUrl, this.title, this.shortDescription,
                    this.healthCheckUrl);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ProjectTagDto {
        @NotBlank
        private String name;

        private Category toDomainModel(){
            return new Category(this.name);
        }
    }

    @Getter
    public static class OverviewDto{
        @NotNull @Valid
        private List<ImageDto> images;
        @NotNull
        private HashMap<PlatformType, String> clientUrl;
        @NotBlank
        private String detailDescription; // 상세 소개

        private Overview toDomainModel(){
            List<Image> imageDomainModelList = images.stream().map(ImageDto::toDomainModel).toList();
            ClientUrl clientUrlDomainModel = new ClientUrl(clientUrl);
            return new Overview(imageDomainModelList, clientUrlDomainModel, this.detailDescription);
        }
    }

    @Getter
    public static class TechviewDto{
        @NotBlank
        private String techTitle; // 개발 영역
        @NotBlank
        private String gitRepositoryUrl; // 깃 링크
        private List<TechStackType> techTagList; // 사용 기술
        private String techDesc; // 개발 상세

        private Techview toDomainModel(){
            return new Techview(this.techTitle, this.gitRepositoryUrl, this.techTagList, this.techDesc);
        }
    }

    @Getter
    public static class ArchitectureDto{
        private ArchitectureType architectureType;
        private String architectureDesc;
        private ImageDto architectureImage;

        private Architecture toDomainModel(){
            return new Architecture(this.architectureType, this.architectureDesc, this.architectureImage.toDomainModel());
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
            return new Participant(this.memberId, this.position, this.isLeader, null);
        }
    }
}
