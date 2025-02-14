package com.eod.sitree.comment.domain.modelrepository;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.model.CommentType;
import com.eod.sitree.comment.ui.dto.response.CommentResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findByCommentId(Long commentId);

    Boolean existParentByParentCommentId(Long parentCommentId, CommentType commentType);

    List<Comment> findByProjectId(Long projectId);

    List<CommentResponseDto> findChildCommentsByProjectIdAndParentCommentId(Long projectId, Long parentCommentId);

    Page<Comment> findByProjectIdAsPage(Long projectId, Pageable pageable);

    Page<CommentResponseDto> findDtoByProjectIdAsPage(Long projectId, Pageable pageable);

    Boolean existsByCommentId(Long commentId);
}
