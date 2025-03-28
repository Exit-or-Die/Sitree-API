package com.eod.sitree.project.domain.modelRepository;

import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.ui.dto.request.ProjectListRequestDto;
import com.eod.sitree.project.ui.dto.response.ParticipatedProjectsResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectDetailResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectLeaderResponseDto;
import com.eod.sitree.project.ui.dto.response.ProjectListResponseDto.ProjectDisplayElement;
import com.eod.sitree.project.ui.dto.response.SitreePickGetResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectRepository {

    Project getProjectById(long projectId);

    ProjectDetailResponseDto getProjectDetailByProjectId(long projectId);

    Long save(Project project);

    Long update(long projectId, Project project);

    void getListByParticipantId();

    Page<ProjectDisplayElement> getListBySearchType(Pageable pageable, ProjectListRequestDto type);

    void plusViewCount(long projectId, String userIp);

    boolean existsById(long projectId);

    boolean toggleLike(long projectId, long memberId);

    List<SitreePickGetResponse> getSitreeSuggestion();

    List<ParticipatedProjectsResponseDto> getParticipatedProjects(long memberId);

    ProjectLeaderResponseDto getProjectLeader(Long projectId);

    void deleteProject(long projectId);
}
