package com.eod.sitree.project.service;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.type.TechStackType;
import com.eod.sitree.project.domain.modelRepository.ProjectLikesRepository;
import com.eod.sitree.project.domain.modelRepository.ProjectRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.ClientRequestServiceImpl;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import com.eod.sitree.project.ui.dto.request.ProjectListRequestDto;
import com.eod.sitree.project.ui.dto.request.ProjectUpdateRequestDto;
import com.eod.sitree.project.ui.dto.response.ParticipatedProjectsResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectCreateResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectDeleteResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectDetailResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectLeaderResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectLikesResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectListResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectMemberLikeResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectUpdateResponseDto;
import com.eod.sitree.project.ui.dto.response.SitreePickGetResponse;
import com.eod.sitree.project.ui.dto.response.TechStackListResponseDto;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
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
    private final ProjectLikesRepository projectLikesRepository;

    @Transactional
    public ProjectCreateResponseDto createProject(ProjectCreateRequestDto projectCreateRequestDto) {
        Project project = projectCreateRequestDto.toDomainModel();
        long savedProjectId = projectRepository.save(project);
        return new ProjectCreateResponseDto(savedProjectId);
    }

    public ProjectDetailResponseDto getProjectDetail(long projectId, Long memberId){
        ProjectDetailResponseDto project = projectRepository.getProjectDetailByProjectId(projectId);
        if (project == null) {
            throw new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_WITH_SUCH_PROJECT_ID);
        }

        boolean healthy = clientRequestService.isHealthy(project.getHead().getHealthCheckUrl());
        var result = projectLikesRepository.getProjectLikeInfo(projectId, memberId);

        project.setLikeInfo(result.getLikeCount(), result.getIsLiked());
        project.setHealthCheck(healthy);

        return project;
    }

    @Transactional
    public void addViewCount(long projectId, String userIp) {
        projectRepository.plusViewCount(projectId, userIp);
    }

    @Transactional
    public ProjectUpdateResponseDto updateProject(long projectId, ProjectUpdateRequestDto projectUpdateRequestDto){
        Project updateProject = projectUpdateRequestDto.toDomainModel();
        Long updatedProjectId = projectRepository.update(projectId, updateProject);
        return new ProjectUpdateResponseDto(updatedProjectId);
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
        result.getContent().parallelStream().forEach(p -> {
            boolean healthy = clientRequestService.isHealthy(p.getHealthCheckUrl());
            p.setIsHealthy(healthy);
        });
        return new ProjectListResponseDto(result.getNumber(), result.isLast(), result.getContent());
    }

    public void validateProjectExist(Long projectId) {

        if (!projectRepository.existsById(projectId)) {
            throw new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_WITH_SUCH_PROJECT_ID);
        }
    }

    public List<SitreePickGetResponse> getSitreeSuggestion() {
        return projectRepository.getSitreeSuggestion();
    }

    public List<ParticipatedProjectsResponseDto> getParticipatedProjects(Long memberId){
        List<ParticipatedProjectsResponseDto> participatedProjects = projectRepository.getParticipatedProjects(
                memberId);
        participatedProjects.parallelStream().forEach(p -> {
            boolean healthy = clientRequestService.isHealthy(p.getHealthCheckUrl());
            p.setIsHealthy(healthy);
        });
        return participatedProjects;
    }

    public ProjectMemberLikeResponseDto getProjectMemberLike(Long projectId, Long memberId) {
        if (memberId == null) {
            return new ProjectMemberLikeResponseDto(false);
        }
        Boolean memberProjectLike = projectLikesRepository.isMemberProjectLike(projectId, memberId);
        return new ProjectMemberLikeResponseDto(memberProjectLike);
    }

    public TechStackListResponseDto getTechStackList() {
        TechStackType[] values = TechStackType.values();
        List<String> techStackList = Arrays.stream(values).map(TechStackType::name).toList();
        return new TechStackListResponseDto(techStackList);
    }

    public ProjectLeaderResponseDto getProjectLeader(Long projectId) {

        return projectRepository.getProjectLeader(projectId);
    }

    @Transactional
    public ProjectDeleteResponseDto projectDelete(Long projectId, Member member) {
        Project project = projectRepository.getProjectById(projectId);
        if (!project.isLeader(member.getMemberId())) {
            throw new ProjectException(ApplicationErrorType.NOT_MATCH_PROJECT_LEADER);
        }
        projectRepository.deleteProject(projectId);
        return new ProjectDeleteResponseDto(projectId);
    }
}
