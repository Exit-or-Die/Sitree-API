package com.eod.sitree.comment.infra;

import static com.eod.sitree.comment.infra.entity.QCommentEntity.commentEntity;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.model.CommentType;
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
        return commentJpaRepository.save(new CommentEntity(comment)).toDomainModel();
    }

    @Override
    public Comment findByCommentId(Long commentId) {

        return Optional.ofNullable(commentJpaRepository.findByCommentId(commentId))
            .orElseThrow(() -> new CommentException(ApplicationErrorType.COMMENT_NOT_FOUND))
            .toDomainModel();
    }

    @Override
    public Boolean existParentByParentCommentId(Long parentCommentId, CommentType commentType) {
        return commentJpaRepository.findByCommentIdAndIsChildCommentAndCommentType(parentCommentId, false, commentType) != null;
    }

    @Override
    public List<Comment> findByProjectId(Long projectId) {

        return Optional.ofNullable(
                commentJpaRepository.findAllByCommentTypeAndTargetIdAndIsChildComment(
                    CommentType.PROJECT, projectId, false))
            .orElseGet(ArrayList::new)
            .stream().map(CommentEntity::toDomainModel)
            .toList();
    }

    @Override
    public Boolean existsByCommentId(Long commentId) {

        return commentJpaRepository.existsById(commentId);
    }
}
