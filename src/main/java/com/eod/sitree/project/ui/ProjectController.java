package com.eod.sitree.project.ui;

import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.project.service.ProjectService;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseDto<?> createProject(@RequestBody @Valid ProjectCreateRequestDto projectCreateRequestDto) {
        var result = projectService.createProject(projectCreateRequestDto);
        return new ResponseDto<>(result);
    }
}
