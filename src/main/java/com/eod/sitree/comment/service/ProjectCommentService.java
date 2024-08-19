package com.eod.sitree.comment.service;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.modelRepository.ProjectRepository;
import com.eod.sitree.project.exeption.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectCommentService {

    private final ProjectRepository projectRepository;

    public void validateProject(Long projectId) {

        if (!projectRepository.existsById(projectId)) {
            throw new ProjectException(ApplicationErrorType.NOT_EXIST_PROJECT_WITH_SUCH_PROJECT_ID);
        }
    }
}
