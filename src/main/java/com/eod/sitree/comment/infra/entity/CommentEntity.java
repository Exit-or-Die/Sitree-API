package com.eod.sitree.comment.infra.entity;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.model.CommentType;
import com.eod.sitree.common.infra.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_comment")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentType commentType;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    @Length(min = 1, max = 1000)
    private String contents;

    @Column(nullable = false)
    private Long createMemberId;

    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    @OneToMany
    @JoinColumn(name = "parent_comment_id")
    private List<CommentEntity> childComments;

    @Column(nullable = false)
    private Boolean isChildComment;

    @Column(nullable = false)
    private Boolean isDeleted;

    public CommentEntity(Comment comment) {
        this.commentType = comment.getCommentType();
        this.commentId = comment.getCommentId();
        this.targetId = comment.getTargetId();
        this.contents = comment.getContents();
        this.createMemberId = comment.getCreateMemberId();
        this.parentCommentId = comment.getParentCommentId();
        this.childComments = toChildCommentEntityList(comment.getChildComments());
        this.isChildComment = comment.getIsChildComment();
        this.isDeleted = comment.getIsDeleted();
    }

    public Comment toDomainModel() {
        return new Comment(
            this.getCreatedAt(),
            this.getModifiedAt(),
            this.getIsChildComment() ? null : this.toChildCommentList(),
            this.getCreateMemberId(),
            this.getParentCommentId(),
            this.getIsDeleted() ? Comment.DELETED_COMMENT_CONTENTS : this.getContents(),
            this.getTargetId(),
            this.getCommentId(),
            this.getIsChildComment(),
            this.getIsDeleted(),
            this.getCommentType()
        );
    }

    private List<Comment> toChildCommentList() {

        return Optional.ofNullable(this.childComments)
            .orElseGet(ArrayList::new)
            .stream()
            .map(CommentEntity::toDomainModel)
            .collect(Collectors.toList());
    }

    private List<CommentEntity> toChildCommentEntityList(List<Comment> childComments) {

        return Optional.ofNullable(childComments)
            .orElseGet(ArrayList::new)
            .stream()
            .map(CommentEntity::new)
            .toList();
    }
}
