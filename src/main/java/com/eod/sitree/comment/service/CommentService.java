package com.eod.sitree.comment.service;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.modelrepository.CommentRepository;
import com.eod.sitree.comment.exception.CommentException;
import com.eod.sitree.comment.ui.dto.CommentCreateRequestDto;
import com.eod.sitree.comment.ui.dto.CommentCreateResponseDto;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto, Member member) {

        // TODO: project 존재 validate
        Comment comment = new Comment(commentCreateRequestDto, member);

        if (commentCreateRequestDto.getIsChildComment()) {

            Comment parentComment = Optional.ofNullable(commentRepository.findByCommentId(commentCreateRequestDto.getParentCommentId()))
                .orElseThrow(() -> new CommentException(ApplicationErrorType.COMMENT_NOT_FOUND, HttpStatus.BAD_REQUEST));

            comment = new Comment(comment, parentComment);
        }

        commentRepository.save(comment);

        return new CommentCreateResponseDto(true);
    }
}
