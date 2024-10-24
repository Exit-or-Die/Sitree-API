package com.eod.sitree.project.infra;

import static com.eod.sitree.project.infra.entity.QProjectEntity.projectEntity;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.model.Architecture;
import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Techview;
import com.eod.sitree.project.domain.model.type.TechStackType;
import com.eod.sitree.project.domain.modelRepository.CategoryRepository;
import com.eod.sitree.project.domain.modelRepository.ParticipantRepository;
import com.eod.sitree.project.domain.modelRepository.ProjectRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.entity.ArchitectureEntity;
import com.eod.sitree.project.infra.entity.ProjectEntity;
import com.eod.sitree.project.infra.entity.ProjectLikesEntity;
import com.eod.sitree.project.infra.entity.ProjectTechStackEntity;
import com.eod.sitree.project.infra.entity.TechviewEntity;
import com.eod.sitree.project.infra.jpa_interfaces.ArchitectureJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ProjectJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ProjectLikesRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ProjectTechStackJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.TechviewJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final ParticipantRepository participantRepository;
    private final ProjectJpaRepository projectJpaRepository;
    private final CategoryRepository categoryRepository;
    private final TechviewJpaRepository techviewJpaRepository;
    private final ProjectTechStackJpaRepository techStackJpaRepository;
    private final ProjectLikesRepository projectLikesRepository;
    private final ArchitectureJpaRepository architectureJpaRepository;


    @Override
    public Project getById(long projectId) {
        ProjectEntity projectEntity = projectJpaRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_WITH_SUCH_PROJECT_ID));
        List<Category> categories = categoryRepository.findAllByProjectId(projectId);
        List<Participant> participants = participantRepository.findAllByProjectId(projectId);
        List<Techview> techviews = techviewJpaRepository.findAllByProjectId(projectId)
                .stream().map((techviewEntity -> {
                    List<TechStackType> techStackTypes = techStackJpaRepository.findAllByTechviewId(techviewEntity.getTechviewId())
                            .stream().map(ProjectTechStackEntity::toDomainModel).toList();
                    return techviewEntity.toDomainModel(techStackTypes);
                })).toList();
        List<Architecture> architectures = architectureJpaRepository.findAllByProjectId(projectId).stream()
                .map(ArchitectureEntity::toDomainModel).toList();
        return projectEntity.toDomainModel(categories, techviews, architectures, participants);
    }

    @Override
    public Long save(Project project) {
        // 프로젝트(Head, Overview) 저장
        ProjectEntity projectEntity = projectJpaRepository.save(new ProjectEntity(project));
        Long projectId = projectEntity.getProjectId();

        // 카테고리 저장
        categoryRepository.saveAllProjectCategoryIds(projectId, project.getCategories());

        // 참여자 저장
        participantRepository.saveAll(project.getParticipants(), projectId);

        // techview 저장
        project.getTechviews().forEach(techview -> {
            TechviewEntity techviewEntity = techviewJpaRepository.save(new TechviewEntity(projectId, techview));
            Long techviewId = techviewEntity.getTechviewId();

            // tech stack 저장
            List<ProjectTechStackEntity> techStackEntityList = techview.getTechStackTypes().stream()
                    .map(t -> new ProjectTechStackEntity(techviewId, t)).toList();
            techStackJpaRepository.saveAll(techStackEntityList);
        });

        // architecture 저장
        List<ArchitectureEntity> architectureEntities = project.getArchitectures().stream()
                .map(a -> new ArchitectureEntity(a, projectId)).toList();
        architectureJpaRepository.saveAll(architectureEntities);
        return projectId;
    }

    @Override
    public void update(long projectId, Project project) {

    }

    @Override
    public void getListByParticipantId() {

    }

    @Override
    @Cacheable(value = "projectViewCount")
    public void plusViewCount(long projectId, String userIp) {
        jpaQueryFactory.update(projectEntity)
                .where(projectEntity.projectId.eq(projectId))
                .set(projectEntity.viewCount, projectEntity.viewCount.add(1))
                .execute();
    }

    @Override
    public boolean existsById(long projectId) {
        return projectJpaRepository.existsById(projectId);
    }

    @Override
    public boolean toggleLike(long projectId, long memberId) {
        ProjectLikesEntity projectLikes = projectLikesRepository.findByProjectIdAndMemberId(
                projectId, memberId).orElseGet(() -> new ProjectLikesEntity(projectId, memberId));
        projectLikes.toggleLike();
        ProjectLikesEntity save = projectLikesRepository.save(projectLikes);
        return save.getIsLiked();
    }
}
