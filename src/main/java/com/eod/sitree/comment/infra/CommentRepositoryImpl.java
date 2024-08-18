package com.eod.sitree.comment.infra;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.modelrepository.CommentRepository;
import com.eod.sitree.comment.exception.CommentException;
import com.eod.sitree.comment.infra.entity.CommentEntity;
import com.eod.sitree.common.exception.ApplicationErrorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {

        return commentJpaRepository.save(new CommentEntity(comment)).to();
    }

    @Override
    public Comment findByCommentId(Long commentId) {

        return Optional.ofNullable(commentJpaRepository.findByCommentId(commentId))
            .orElseThrow(() -> new CommentException(ApplicationErrorType.COMMENT_NOT_FOUND))
            .to();
    }

    @Override
    public List<Comment> findByProjectId(Long projectId) {

        return Optional.ofNullable(commentJpaRepository.findAllByProjectId(projectId))
            .orElseGet(ArrayList::new)
            .stream().map(CommentEntity::to)
            .toList();
    }
}
