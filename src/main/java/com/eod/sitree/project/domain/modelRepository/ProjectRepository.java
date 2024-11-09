package com.eod.sitree.project.domain.modelRepository;

import com.eod.sitree.project.domain.model.Project;
import java.util.List;

public interface ProjectRepository {

    Project getById(long projectId);

    Long save(Project project);

    void update(long projectId, Project project);

    void getListByParticipantId();

    void plusViewCount(long projectId, String userIp);

    boolean existsById(long projectId);

}
