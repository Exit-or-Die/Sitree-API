package com.eod.sitree.project.ui;

import com.eod.sitree.auth.infra.resolver.MemberPrincipal;
import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.project.service.ProjectService;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import com.eod.sitree.project.ui.dto.request.ProjectListRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseDto<?> createProject(
            @RequestBody @Valid ProjectCreateRequestDto projectCreateRequestDto) {
        var result = projectService.createProject(projectCreateRequestDto);
        return new ResponseDto<>(result);
    }

    @AuthNotRequired
    @GetMapping("/{projectId}")
    public ResponseDto<?> getProjectDetail(@PathVariable("projectId") long projectId
            , HttpServletRequest request) {
        projectService.addViewCount(projectId, request.getRemoteAddr());
        var result = projectService.getProjectDetail(projectId);
        return new ResponseDto<>(result);
    }

    @GetMapping
    @AuthNotRequired
    public ResponseDto<?> getProjectList(@ModelAttribute ProjectListRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), 10);
        var result = projectService.getProjectList(pageable, dto.getSortType());
        return new ResponseDto<>(result);
    }

    @PostMapping("{projectId}/likes")
    public ResponseDto<?> toggleProjectLikes(@PathVariable Long projectId, @MemberPrincipal Member member){
        var result = projectService.toggleProjectLikes(projectId, member.getMemberId());
        return new ResponseDto<>(result);
    }
}
