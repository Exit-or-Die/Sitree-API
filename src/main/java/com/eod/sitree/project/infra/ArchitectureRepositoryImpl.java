package com.eod.sitree.project.infra;

import com.eod.sitree.project.domain.model.Architecture;
import com.eod.sitree.project.domain.model.type.ArchitectureType;
import com.eod.sitree.project.domain.modelRepository.ArchitectureRepository;
import com.eod.sitree.project.infra.entity.ArchitectureEntity;
import com.eod.sitree.project.infra.jpa_interfaces.ArchitectureJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArchitectureRepositoryImpl implements ArchitectureRepository {

    private final ArchitectureJpaRepository architectureJpaRepository;

    @Override
    public void updateProjectArchitecture(long projectId, List<Architecture> updateArchitectures) {
        Map<ArchitectureType, Architecture> updateArchitectureMap = updateArchitectures.stream()
                .collect(Collectors.toMap(Architecture::getArchitectureType, a -> a));

        List<ArchitectureEntity> architectureEntityList = architectureJpaRepository.findAllByProjectId(
                projectId);

        List<ArchitectureEntity> updateArchitectureEntityList = new ArrayList<>();
        List<ArchitectureEntity> deleteArchitectureEntityList = new ArrayList<>();

        architectureEntityList.forEach(a -> {
            if (updateArchitectureMap.containsKey(a.getArchitectureType())) {
                a.updateArchitectureEntity(updateArchitectureMap.get(a.getArchitectureType()));
                updateArchitectureEntityList.add(a);
                updateArchitectureMap.remove(a.getArchitectureType());
            } else {
                deleteArchitectureEntityList.add(a);
            }
        });

        updateArchitectureMap.values().forEach(a -> {
            updateArchitectureEntityList.add(new ArchitectureEntity(a, projectId));
        });

        if (!deleteArchitectureEntityList.isEmpty()) {
            architectureJpaRepository.deleteAll(deleteArchitectureEntityList);
        }
        if (!updateArchitectureEntityList.isEmpty()) {
            architectureJpaRepository.saveAll(updateArchitectureEntityList);
        }
    }
}
