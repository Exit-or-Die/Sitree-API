package com.eod.sitree.project.service;

import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.modelRepository.ProjectRepository;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import com.eod.sitree.project.ui.dto.response.ProjectCreateResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectDetailResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectCreateResponseDto createProject(ProjectCreateRequestDto projectCreateRequestDto) {
        Project project = new Project(projectCreateRequestDto);
        long savedProjectId = projectRepository.save(project);
        return new ProjectCreateResponseDto(savedProjectId);
    }

    public ProjectDetailResponseDto getDeleteProject(long projectId){
        Project project = projectRepository.getById(projectId);
        return new ProjectDetailResponseDto(project);
    }

    public void updateProject(){

    }

    public void exportProject(){

    }
}
