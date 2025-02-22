package com.eod.sitree.project.infra;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.model.Techview;
import com.eod.sitree.project.domain.modelRepository.TechviewRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.entity.ProjectTechStackEntity;
import com.eod.sitree.project.infra.entity.TechviewEntity;
import com.eod.sitree.project.infra.jpa_interfaces.ProjectTechStackJpaRepository;
import com.eod.sitree.project.infra.jpa_interfaces.TechviewJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TechviewRepositoryImpl implements TechviewRepository {

    private final TechviewJpaRepository techviewJpaRepository;
    private final ProjectTechStackJpaRepository techStackJpaRepository;

    @Override
    public void updateProjectTechviews(long projectId, List<Techview> updateTechviews) {
        // 업데이트될 techview ID 배열 추출 (null 포함)
        List<Long> updateTechviewIds = updateTechviews.stream().map(Techview::getTechviewId)
                .toList();

        List<TechviewEntity> techviewEntities = techviewJpaRepository.findAllByProjectId(projectId);

        // 기존 techview 중 update 대상 아닌 경우 삭제
        List<TechviewEntity> deleteTechviewEntityList = techviewEntities.stream()
                .filter(t -> !updateTechviewIds.contains(t.getTechviewId()))
                .toList();
        List<Long> deleteTechviewIdList = deleteTechviewEntityList.stream()
                .map(TechviewEntity::getTechviewId).toList();

        if (!deleteTechviewEntityList.isEmpty()) {
            techviewJpaRepository.deleteAll(deleteTechviewEntityList);
            techStackJpaRepository.deleteByTechviewIdIn(deleteTechviewIdList);
        }

        // 수정/신규 반영
        List<ProjectTechStackEntity> updateProjectTechStackEntityList = new ArrayList<>();

        updateTechviews.forEach(t -> {
            if (t.getTechviewId() == null) { // 신규 techview
                TechviewEntity techviewEntity = new TechviewEntity(projectId, t);
                TechviewEntity savedTechviewEntity = techviewJpaRepository.save(techviewEntity);
                long techviewId = savedTechviewEntity.getTechviewId();
                List<ProjectTechStackEntity> techStackEntityList = t.getTechStackTypes().stream()
                        .map(ts -> new ProjectTechStackEntity(techviewId, ts)).toList();
                techStackJpaRepository.saveAll(techStackEntityList);
            } else { // 기존 techview
                // techview 업데이트
                TechviewEntity techviewEntity = techviewEntities.stream()
                        .filter(te -> Objects.equals(te.getTechviewId(),
                                t.getTechviewId())).findFirst().orElseThrow(() -> new ProjectException(
                                ApplicationErrorType.TECHVIEW_UPDATE_ERROR));
                techviewEntity.updateProjectTechview(t);

                // project tech stack 업데이트
                techStackJpaRepository.deleteAllByTechviewId(t.getTechviewId());
                t.getTechStackTypes().forEach(ts -> {
                    updateProjectTechStackEntityList.add(
                            new ProjectTechStackEntity(t.getTechviewId(), ts));
                });
            }
        });
        if (!updateProjectTechStackEntityList.isEmpty()) {
            techStackJpaRepository.saveAll(updateProjectTechStackEntityList);
        }
    }
}
