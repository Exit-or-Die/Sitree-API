package com.eod.sitree.project.infra;

import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.modelRepository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;

    @Override
    public Project getById(long projectId) {
        return null;
    }

    @Override
    public void save(Project project) {

    }

    @Override
    public void update(long projectId, Project project) {

    }
}
