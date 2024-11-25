package com.eod.sitree.project.domain.modelRepository;

import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.ui.dto.request.ProjectListRequestDto;
import com.eod.sitree.project.ui.dto.response.ProjectListResponseDto.ProjectDisplayElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectRepository {

    Project getById(long projectId);

    Long save(Project project);

    void update(long projectId, Project project);

    void getListByParticipantId();

    Page<ProjectDisplayElement> getListBySearchType(Pageable pageable, ProjectListRequestDto type);

    void plusViewCount(long projectId, String userIp);

    boolean existsById(long projectId);

    boolean toggleLike(long projectId, long memberId);
}
