package com.eod.sitree.project.service;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.modelRepository.ProjectRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.ClientRequestServiceImpl;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import com.eod.sitree.project.ui.dto.request.ProjectListRequestDto;
import com.eod.sitree.project.ui.dto.response.ProjectCreateResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectDetailResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectLikesResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectListResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRequestServiceImpl clientRequestService;

    @Transactional
    public ProjectCreateResponseDto createProject(ProjectCreateRequestDto projectCreateRequestDto) {
        Project project = projectCreateRequestDto.toDomainModel();
        long savedProjectId = projectRepository.save(project);
        return new ProjectCreateResponseDto(savedProjectId);
    }

    public ProjectDetailResponseDto getProjectDetail(long projectId){
        Project project = projectRepository.getById(projectId);
        boolean healthy = clientRequestService.isHealthy(project.getHealthCheckUrl());
        return new ProjectDetailResponseDto(project, healthy);
    }

    @Transactional
    public void addViewCount(long projectId, String userIp) {
        projectRepository.plusViewCount(projectId, userIp);
    }

    public void updateProject(){

    }

    public void exportProject(){

    }

    @Transactional
    public ProjectLikesResponseDto toggleProjectLikes(Long projectId, Long memberId){
        boolean isLiked = projectRepository.toggleLike(projectId, memberId);
        return new ProjectLikesResponseDto(isLiked);
    }

    public ProjectListResponseDto getProjectList(Pageable pageable, ProjectListRequestDto dto) {
        var result = projectRepository.getListBySearchType(pageable, dto);
        return new ProjectListResponseDto(result.getNumber(), result.isLast(), result.getContent());
    }

    public void validateProjectExist(Long projectId) {

        if (!projectRepository.existsById(projectId)) {
            throw new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_WITH_SUCH_PROJECT_ID);
        }
    }
}
