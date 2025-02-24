package com.eod.sitree.member.ui.dto.request;

import com.eod.sitree.member.domain.model.Career;
import com.eod.sitree.member.domain.model.EducationActivity;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.MyPage;
import com.eod.sitree.member.domain.model.Project;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.domain.model.SelfIntroduction;
import com.eod.sitree.member.domain.model.type.EducationActivityCategoryType;
import com.eod.sitree.member.domain.model.type.RoleTagType;
import com.eod.sitree.project.domain.model.type.TechStackType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberUpdateRequestDto {

    private String email;

    private Provider provider;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String profileImgUrl;

    @Nullable
    private String thirdPartyProfileUrl;

    @Nullable
    private Long belongingId;

    @Nullable
    private MyPageDto myPage;

    public Member toDomain() {

        return new Member(
            provider,
            nickname,
            email,
            profileImgUrl,
            thirdPartyProfileUrl,
            belongingId,
            Optional.ofNullable(myPage)
                .map(MyPageDto::toDomain)
                .orElseGet(MyPage::new)
        );
    }

    @Getter
    @NoArgsConstructor
    public static class MyPageDto {

        private SelfIntroductionDto selfIntroduction;

        private List<CareerDto> careers;

        private List<EducationActivityDto> educationActivities;

        private List<TechStackType> techStacks;

        private List<String> links;

        public MyPage toDomain() {

            return new MyPage(
                selfIntroduction.toDomain(),
                Optional.ofNullable(careers).orElseGet(ArrayList::new)
                    .stream()
                    .map(CareerDto::toDomain)
                    .toList(),
                Optional.ofNullable(educationActivities).orElseGet(ArrayList::new)
                    .stream()
                    .map(EducationActivityDto::toDomain)
                    .toList(),
                techStacks,
                links
            );
        }
    }

    @Getter
    public static class SelfIntroductionDto {

        private String title;

        private String contents;

        public SelfIntroduction toDomain() {

            return new SelfIntroduction(title, contents);
        }
    }

    @Getter
    public static class CareerDto {

        private String careerName;

        private LocalDateTime startedAt;

        private LocalDateTime endedAt;

        private String position;

        private String department;

        private List<ProjectDto> projects;

        public Career toDomain() {

            return new Career(
                careerName,
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

    public static class EducationActivityDto {

        private String educationActivityName;

        private LocalDateTime startedAt;

        private LocalDateTime endedAt;

        private String majorOrOrganization;

        private EducationActivityCategoryType category;

        private String contents;

        public EducationActivity toDomain() {

            return new EducationActivity(educationActivityName, startedAt, endedAt,
                majorOrOrganization, category, contents);
        }
    }
}
