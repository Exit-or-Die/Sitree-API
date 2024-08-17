package com.eod.sitree.project.infra;

import static com.eod.sitree.project.infra.entity.QProjectEntity.projectEntity;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Techview;
import com.eod.sitree.project.domain.modelRepository.ProjectRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.entity.FocusPointEntity;
import com.eod.sitree.project.infra.entity.ParticipantEntity;
import com.eod.sitree.project.infra.entity.ProjectEntity;
import com.eod.sitree.project.infra.entity.ProjectTechStackEntity;
import com.eod.sitree.project.infra.entity.TagEntity;
import com.eod.sitree.project.infra.entity.TechviewEntity;
import com.eod.sitree.project.infra.jpa_interfaces.FocusPointJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ParticipantJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ProjectJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.ProjectTechStackJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.TagJpaRepository;
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

    private final ProjectJpaRepository projectJpaRepository;
    private final TagJpaRepository tagJpaRepository;
    private final ParticipantJpaRepository participantJpaRepository;
    private final TechviewJpaRepository techviewJpaRepository;
    private final FocusPointJpaRepository focusPointJpaRepository;
    private final ProjectTechStackJpaRepository techStackJpaRepository;


    @Override
    public Project getById(long projectId) {
        ProjectEntity projectEntity = projectJpaRepository.findById(projectId).orElseThrow(
                () -> new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_WITH_SUCH_PROJECT_ID)
        );
        List<TagEntity> tagEntities = tagJpaRepository.findAllByProjectId(projectId);
        List<ParticipantEntity> participantEntities = participantJpaRepository.findAllByProjectId(projectId);
        List<TechviewEntity> techviewEntities = techviewJpaRepository.findAllByProjectId(projectId);
        List<Techview> techviews = techviewEntities.stream().map((techviewEntity -> {
            List<FocusPointEntity> focusPointEntities = focusPointJpaRepository.findAllByTechviewId(
                    techviewEntity.getTechviewId());
            List<ProjectTechStackEntity> techStackEntities = techStackJpaRepository.findAllByTechviewId(
                    techviewEntity.getTechviewId());
            return new Techview(techviewEntity, techStackEntities, focusPointEntities);
        })).toList();

        return new Project(projectEntity, tagEntities, techviews, participantEntities);
    }

    @Override
    public Long save(Project project) {
        // 프로젝트(Head, Overview) 저장
        ProjectEntity projectEntity = projectJpaRepository.save(new ProjectEntity(project));
        Long projectId = projectEntity.getProjectId();

        // 태그 저장
        List<TagEntity> tagEntityList = project.getTags().stream().map(t -> new TagEntity(projectId, t))
                .toList();
        tagJpaRepository.saveAll(tagEntityList);

        // 참여자 저장
        List<ParticipantEntity> participantEntityList = project.getParticipants().stream()
                .map(p -> new ParticipantEntity(projectId, p)).toList();
        participantJpaRepository.saveAll(participantEntityList);

        // techview 저장
        project.getTechviews().forEach(techview -> {
            TechviewEntity techviewEntity = techviewJpaRepository.save(new TechviewEntity(projectId, techview));
            Long techviewId = techviewEntity.getTechviewId();

            // focus point 저장
            List<FocusPointEntity> focusPointEntityList = techview.getFocusedPoints().stream()
                    .map(f -> new FocusPointEntity(techviewId, f)).toList();
            focusPointJpaRepository.saveAll(focusPointEntityList);

            // tech stack 저장
            List<ProjectTechStackEntity> techStackEntityList = techview.getTechStackTypes().stream()
                    .map(t -> new ProjectTechStackEntity(techviewId, t)).toList();
            techStackJpaRepository.saveAll(techStackEntityList);
        });
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
    public void plusViewCount(long projectId, long memberId) {
        jpaQueryFactory.update(projectEntity)
                .where(projectEntity.projectId.eq(projectId))
                .set(projectEntity.viewCount, projectEntity.viewCount.add(1))
                .execute();
    }
}
