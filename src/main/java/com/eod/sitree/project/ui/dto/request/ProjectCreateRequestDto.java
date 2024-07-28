package com.eod.sitree.project.ui.dto.request;

import com.eod.sitree.common.infra.validator.ValidEnum;
import com.eod.sitree.project.domain.model.Image;
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
    private HeadDto headDto;
    @NotNull @Valid
    private List<TagDto> tagDtoList;
    @NotNull @Valid
    private OverviewDto overviewDto;
    @Valid
    private List<TechviewDto> techviewDtoList;

    @Getter
    public static class HeadDto {
        @NotBlank
        private String thumbnailImageUrl;
        @NotBlank
        private String title;
        private String shortDescription;
        @NotBlank
        private String healthCheckUrl;

    }

    @Getter
    @NoArgsConstructor
    public static class TagDto{
        @NotBlank
        private String name;
    }

    @Getter
    public static class OverviewDto{
        @NotNull @Valid
        private List<ImageDto> images;
        @NotNull
        private ClientUrlDto clientUrl;
        @NotNull
        private List<Long> participantIds;
        @NotBlank
        private String detailDescription; // 상세 소개

        @Getter
        public static class ClientUrlDto{
            private String liveWebDomain;
            private HashMap<PlatformType, String> downloadMethods;
        }
    }

    @Getter
    public static class TechviewDto{
        @NotBlank
        private String techArea; // 개발 영역
        @NotBlank
        private String gitRepositoryUrl; // 깃 링크
        private List<TechStackType> techSTackTypes; // 사용 기술
        @Valid
        private ImageDto architectureImage; // 아키텍쳐 이미지
        private String architectureDescription; // 아키텍쳐 구조 설명
        @Valid
        private List<FocusPointDto> focusedPoints; // 핵심 기술 내용

        @Getter
        public static class FocusPointDto{
            @NotBlank
            private Long memberNo;
            @NotBlank
            private String focusedOn;
        }
    }

    @Getter
    public static class ImageDto{
        @NotBlank
        private String imageUrl;
        @ValidEnum(enumClass = ImageType.class)
        private ImageType imageType;
    }
}
