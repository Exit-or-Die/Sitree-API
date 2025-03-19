package com.eod.sitree.member.ui.dto.request;

import com.eod.sitree.member.domain.model.Career;
import com.eod.sitree.member.domain.model.Careers;
import com.eod.sitree.member.domain.model.EducationActivity;
import com.eod.sitree.member.domain.model.EducationActivity.EducationStatus;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.MyLink;
import com.eod.sitree.member.domain.model.MyPage;
import com.eod.sitree.member.domain.model.Project;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.domain.model.SelfIntroduction;
import com.eod.sitree.member.domain.model.type.EducationActivityCategoryType;
import com.eod.sitree.member.domain.model.type.LinkProviderType;
import com.eod.sitree.member.domain.model.type.RoleTagType;
import com.eod.sitree.member.domain.model.type.TechStackType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberUpdateRequestDto {

    private String email;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "Wrong phone number format")
    private String phoneNumber;

    private Provider provider;

    @NotEmpty
    private String nickname;

    private String position;

    @NotEmpty
    private String profileImgUrl;

    @Nullable
    private String thirdPartyProfileUrl;

    @Nullable
    private String shortIntroduction;

    @Nullable
    private Long belongingId;

    @Nullable
    private MyPageDto myPage;

    public Member toDomain() {

        return new Member(
            provider,
            nickname,
            position,
            email,
            phoneNumber,
            profileImgUrl,
            thirdPartyProfileUrl,
            belongingId,
            shortIntroduction,
            Optional.ofNullable(myPage)
                .map(MyPageDto::toDomain)
                .orElseGet(MyPage::new)
        );
    }

    @Getter
    @NoArgsConstructor
    public static class MyPageDto {

        private SelfIntroductionDto selfIntroduction;

        private CareersDto careers;

        private List<EducationActivityDto> educationActivities;

        private List<TechStackType> techStacks;

        private List<MyLinkDto> links;

        public MyPage toDomain() {

            return new MyPage(
                Optional.ofNullable(selfIntroduction)
                    .map(SelfIntroductionDto::toDomain)
                    .orElseGet(SelfIntroduction::new),
                Optional.ofNullable(careers)
                    .map(CareersDto::toDomain)
                    .orElseGet(Careers::new),
                Optional.ofNullable(educationActivities).orElseGet(ArrayList::new)
                    .stream()
                    .map(EducationActivityDto::toDomain)
                    .toList(),
                Optional.ofNullable(techStacks).orElseGet(ArrayList::new),
                Optional.ofNullable(links).orElseGet(ArrayList::new)
                    .stream()
                    .map(MyLinkDto::toDomain)
                    .toList()
            );
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SelfIntroductionDto {

        private String title;

        private String contents;

        public SelfIntroduction toDomain() {

            return new SelfIntroduction(title, contents);
        }
    }

    @Getter
    public static class CareersDto {

        List<CareerDto> careerList;

        public Careers toDomain() {

            return new Careers(0, 0, Optional.ofNullable(careerList)
                .orElseGet(ArrayList::new)
                .stream()
                .map(CareerDto::toDomain)
                .toList()
            );
        }
    }

    @Getter
    public static class CareerDto {

        private Long belongingId;

        private String belongingName;

        private LocalDateTime startedAt;

        private LocalDateTime endedAt;

        private String position;

        private String department;

        private List<ProjectDto> projects;

        public Career toDomain() {

            return new Career(
                belongingId,
                startedAt,
                endedAt,
                position,
                department,
                Optional.ofNullable(projects).orElseGet(ArrayList::new)
                    .stream()
                    .map(ProjectDto::toDomain)
                    .toList()
            );
        }
    }

    @Getter
    public static class ProjectDto {

        private String projectName;

        private LocalDateTime startedAt;

        private LocalDateTime endedAt;

        private String contents;

        private List<RoleTagType> roleTags;

        public Project toDomain() {

            return new Project(projectName, startedAt, endedAt, contents, roleTags);
        }
    }

    @Getter
    public static class EducationActivityDto {

        private String educationActivityName;

        private LocalDateTime startedAt;

        private LocalDateTime endedAt;

        private EducationStatus educationStatus;

        private String majorOrOrganization;

        private EducationActivityCategoryType category;

        private String contents;

        public EducationActivity toDomain() {

            return new EducationActivity(educationActivityName, startedAt, endedAt, educationStatus,
                majorOrOrganization, category, contents);
        }
    }

    @Getter
    public static class MyLinkDto {

        private LinkProviderType linkProvider;

        private String link;

        public MyLink toDomain() {

            return new MyLink(linkProvider, link);
        }
    }
}
