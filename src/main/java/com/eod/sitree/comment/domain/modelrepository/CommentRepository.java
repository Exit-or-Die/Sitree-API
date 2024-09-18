package com.eod.sitree.comment.domain.modelrepository;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.model.CommentType;
import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findByCommentId(Long commentId);

    Boolean existParentByParentCommentId(Long parentCommentId, CommentType commentType);

    List<Comment> findByProjectId(Long projectId);

    Boolean existsByCommentId(Long commentId);
}
