package com.eod.sitree.comment.service;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.modelrepository.CommentRepository;
import com.eod.sitree.comment.exception.CommentException;
import com.eod.sitree.comment.ui.dto.CommentCreateRequestDto;
import com.eod.sitree.comment.ui.dto.CommentCreateResponseDto;
import com.eod.sitree.comment.ui.dto.CommentUpdateRequestDto;
import com.eod.sitree.comment.ui.dto.CommentUpdateResponseDto;
import com.eod.sitree.comment.ui.dto.CommentsResponseDto;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProjectCommentService projectCommentService;

    @Transactional
    public CommentCreateResponseDto createComment(Long projectId, CommentCreateRequestDto commentCreateRequestDto, Member member) {

        projectCommentService.validateProject(projectId);
        Comment comment = new Comment(projectId, commentCreateRequestDto, member);

        if (commentCreateRequestDto.getIsChildComment()) {

            Comment parentComment = Optional.ofNullable(commentRepository.findByCommentId(commentCreateRequestDto.getParentCommentId()))
                .orElseThrow(() -> new CommentException(ApplicationErrorType.COMMENT_NOT_FOUND, HttpStatus.BAD_REQUEST));
            parentComment.validateParent();

            comment = new Comment(comment, parentComment);
        }

        commentRepository.save(comment);

        return new CommentCreateResponseDto(true);
    }

    public List<CommentsResponseDto> findComment(Long projectId) {

        projectCommentService.validateProject(projectId);

        List<Comment> comments = commentRepository.findByProjectId(projectId);
        return comments.stream()
            .map(CommentsResponseDto::new)
            .toList();
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long projectId, CommentUpdateRequestDto commentUpdateRequestDto, Member member) {

        projectCommentService.validateProject(projectId);
        Comment comment = commentRepository.findByCommentId(commentUpdateRequestDto.getCommentId());
        comment.validateCreateMember(member);

        comment.updateContents(commentUpdateRequestDto.getContents());
        commentRepository.save(comment);

        return new CommentUpdateResponseDto(true);
    }
}
