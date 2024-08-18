package com.eod.sitree.comment.infra;

import com.eod.sitree.comment.infra.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    CommentEntity findByCommentId(Long commentId);

    List<CommentEntity> findAllByProjectId(Long projectId);
}
