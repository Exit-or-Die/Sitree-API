package com.eod.sitree.project.ui;

import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.project.service.ProjectService;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseDto<?> createProject(
            @RequestBody @Valid ProjectCreateRequestDto projectCreateRequestDto) {
        var result = projectService.createProject(projectCreateRequestDto);
        return new ResponseDto<>(result);
    }

    @GetMapping("/{projectId}")
    public ResponseDto<?> getProjectDetail(@PathVariable("projectId") long projectId) {
        var result = projectService.getProjectDetail(projectId);
        return new ResponseDto<>(result);
    }

    @GetMapping
    public ResponseDto<?> getProjectList() {
        var result = projectService.getTotalProjects();
        return new ResponseDto<>(result);
    }
}
