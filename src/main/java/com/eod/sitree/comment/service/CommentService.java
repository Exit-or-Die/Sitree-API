package com.eod.sitree.comment.service;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.model.CommentType;
import com.eod.sitree.comment.domain.modelrepository.CommentRepository;
import com.eod.sitree.comment.ui.dto.request.CommentCreateRequestDto;
import com.eod.sitree.comment.ui.dto.response.CommentCreateResponseDto;
import com.eod.sitree.comment.ui.dto.request.CommentUpdateRequestDto;
import com.eod.sitree.comment.ui.dto.response.CommentDeleteResponseDto;
import com.eod.sitree.comment.ui.dto.response.CommentUpdateResponseDto;
import com.eod.sitree.comment.ui.dto.response.CommentsResponseDto;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.project.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProjectService projectService;

    @Transactional
    public CommentCreateResponseDto createProjectComment(Long projectId,
        CommentCreateRequestDto commentCreateRequestDto, Member member) {

        projectService.validateProjectExist(projectId);

        Comment comment = new Comment(
            CommentType.PROJECT,
            projectId,
            commentCreateRequestDto.getContents(),
            commentCreateRequestDto.getParentCommentId(),
            commentCreateRequestDto.getIsChildComment(),
            member
        );

        comment.validateParent(commentRepository.existParentByParentCommentId(
            commentCreateRequestDto.getParentCommentId(), CommentType.PROJECT));

        commentRepository.save(comment);

        return new CommentCreateResponseDto(true);
    }

    public List<CommentsResponseDto> findProjectComment(Long projectId) {

        List<Comment> comments = commentRepository.findByProjectId(projectId);
        return comments.stream()
            .map(CommentsResponseDto::new)
            .toList();
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto, Member member) {

        Comment comment = commentRepository.findByCommentId(commentId);
        comment.updateContents(commentUpdateRequestDto.getContents(), member);
        commentRepository.save(comment);

        return new CommentUpdateResponseDto(true);
    }

    @Transactional
    public CommentDeleteResponseDto deleteComment(Long commentId, Member member) {

        Comment comment = commentRepository.findByCommentId(commentId);
        comment.delete(member);
        commentRepository.save(comment);

        return new CommentDeleteResponseDto(true);
    }
}
