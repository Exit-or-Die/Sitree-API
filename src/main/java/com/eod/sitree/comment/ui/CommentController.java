package com.eod.sitree.comment.ui;

import com.eod.sitree.auth.infra.resolver.MemberPrincipal;
import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.comment.service.CommentService;
import com.eod.sitree.comment.ui.dto.request.CommentCreateRequestDto;
import com.eod.sitree.comment.ui.dto.request.CommentFetchRequest;
import com.eod.sitree.comment.ui.dto.response.CommentCreateResponseDto;
import com.eod.sitree.comment.ui.dto.request.CommentUpdateRequestDto;
import com.eod.sitree.comment.ui.dto.response.CommentDeleteResponseDto;
import com.eod.sitree.comment.ui.dto.response.CommentUpdateResponseDto;
import com.eod.sitree.comment.ui.dto.response.CommentsResponseDto;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.domain.model.Member;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/project/{projectId}")
    public ResponseDto<CommentCreateResponseDto> createProjectComment(@PathVariable Long projectId, @Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto, @MemberPrincipal Member member) {

        return ResponseDto.ok(commentService.createProjectComment(projectId, commentCreateRequestDto, member));
    }

    @AuthNotRequired
    @GetMapping("/project/{projectId}")
    public ResponseDto<CommentsResponseDto> findProjectComments(@PathVariable Long projectId, CommentFetchRequest request) {

        return ResponseDto.ok(commentService.findProjectCommentAsPage(projectId, request));
    }

    @PutMapping("/{commentId}")
    public ResponseDto<CommentUpdateResponseDto> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentUpdateRequestDto commentUpdateRequestDto, @MemberPrincipal Member member) {

        return ResponseDto.ok(commentService.updateComment(commentId, commentUpdateRequestDto, member));
    }

    @DeleteMapping("/{commentId}")
    public ResponseDto<CommentDeleteResponseDto> deleteComment(@PathVariable Long commentId, @MemberPrincipal Member member) {

        return ResponseDto.ok(commentService.deleteComment(commentId, member));
    }
}
