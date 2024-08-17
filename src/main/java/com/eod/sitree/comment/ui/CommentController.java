package com.eod.sitree.comment.ui;

import com.eod.sitree.auth.infra.resolver.MemberPrincipal;
import com.eod.sitree.comment.service.CommentService;
import com.eod.sitree.comment.ui.dto.CommentCreateRequestDto;
import com.eod.sitree.comment.ui.dto.CommentCreateResponseDto;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.domain.model.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public ResponseDto<CommentCreateResponseDto> createComment(@Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto, @MemberPrincipal Member member) {

        return ResponseDto.ok(commentService.createComment(commentCreateRequestDto, member));
    }
}
