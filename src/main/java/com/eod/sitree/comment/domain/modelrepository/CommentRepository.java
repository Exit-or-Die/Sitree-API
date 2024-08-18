package com.eod.sitree.comment.domain.modelrepository;

import com.eod.sitree.comment.domain.model.Comment;
import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findByCommentId(Long commentId);

    List<Comment> findByProjectId(Long projectId);
}
