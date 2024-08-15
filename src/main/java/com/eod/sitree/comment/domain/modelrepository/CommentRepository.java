package com.eod.sitree.comment.domain.modelrepository;

import com.eod.sitree.comment.domain.model.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findByCommentId(Long commentId);
}
