package com.eod.sitree.project.ui;

import com.eod.sitree.auth.infra.resolver.MemberPrincipal;
import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.project.service.ProjectService;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto;
import com.eod.sitree.project.ui.dto.request.ProjectListRequestDto;
import com.eod.sitree.project.ui.dto.request.ProjectUpdateRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
    public ResponseDto<?> getProjectDetail(@PathVariable("projectId") long projectId,
            @RequestParam(required = false) Long memberId
            , HttpServletRequest request) {
        projectService.addViewCount(projectId, request.getRemoteAddr());
        var result = projectService.getProjectDetail(projectId, memberId);
        return new ResponseDto<>(result);
    }

    @GetMapping
    @AuthNotRequired
    public ResponseDto<?> getProjectList(ProjectListRequestDto dto) {
        Pageable pageable = dto.getPageable();
        var result = projectService.getProjectList(pageable, dto);
        return new ResponseDto<>(result);
    }

    @PostMapping("{projectId}/likes")
    public ResponseDto<?> toggleProjectLikes(@PathVariable Long projectId,
            @MemberPrincipal Member member) {
        var result = projectService.toggleProjectLikes(projectId, member.getMemberId());
        return new ResponseDto<>(result);
    }

    @AuthNotRequired
    @GetMapping("/sitree-pick")
    public ResponseDto<?> getSitreeSuggestion() {
        var result = projectService.getSitreeSuggestion();
        return new ResponseDto<>(result);
    }

    @AuthNotRequired
    @GetMapping("/participants/{memberId}")
    public ResponseDto<?> getParticipatedProjects(@PathVariable Long memberId) {
        var result = projectService.getParticipatedProjects(memberId);
        return new ResponseDto<>(result);
    }

    @AuthNotRequired
    @GetMapping("/{projectId}/likes/check")
    public ResponseDto<?> getProjectMemberLike(@PathVariable Long projectId,
            @RequestParam(required = false) Long memberId) {
        var result = projectService.getProjectMemberLike(projectId, memberId);
        return new ResponseDto<>(result);
    }

    @PutMapping("/{projectId}")
    public ResponseDto<?> updateProject(@PathVariable Long projectId,
            @RequestBody @Valid ProjectUpdateRequestDto projectUpdateRequestDto) {
        var result = projectService.updateProject(projectId, projectUpdateRequestDto);
        return new ResponseDto<>(result);
    }

    @AuthNotRequired
    @GetMapping("/tech-stacks")
    public ResponseDto<?> getTechStackList() {
        var result = projectService.getTechStackList();
        return new ResponseDto<>(result);
    }

    @AuthNotRequired
    @GetMapping("/{projectId}/leader")
    public ResponseDto<?> getProjectLeader(@PathVariable Long projectId) {
        var result = projectService.getProjectLeader(projectId);
        return new ResponseDto<>(result);
    }

    @DeleteMapping("/{projectId}")
    public ResponseDto<?> deleteProject(@PathVariable Long projectId, @MemberPrincipal Member member) {
        var result = projectService.projectDelete(projectId, member);
        return new ResponseDto<>(result);
    }
}
