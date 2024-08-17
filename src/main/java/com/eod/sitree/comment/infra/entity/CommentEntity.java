package com.eod.sitree.comment.infra.entity;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.common.infra.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_comment")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    @Length(min = 1, max = 1000)
    private String contents;

    @Column(nullable = false)
    private Long createMemberId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private CommentEntity parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CommentEntity> childComments;

    @Column(nullable = false)
    private Boolean isChildComment;

    public CommentEntity(Long commentId, Long projectId, String contents, Long createMemberId,
        CommentEntity parentComment, List<CommentEntity> childComments, Boolean isChildComment) {
        this.commentId = commentId;
        this.projectId = projectId;
        this.contents = contents;
        this.createMemberId = createMemberId;
        this.parentComment = parentComment;
        this.childComments = childComments;
        this.isChildComment = isChildComment;
    }

    public CommentEntity(Comment comment) {
        this.commentId = comment.getCommentId();
        this.projectId = comment.getProjectId();
        this.contents = comment.getContents();
        this.createMemberId = comment.getCreateMemberId();
        this.parentComment = Objects.isNull(comment.getParentComment()) ? null : new CommentEntity(comment.getParentComment());
        this.childComments = toChildCommentEntityList(comment.getChildComments());
        this.isChildComment = comment.getIsChildComment();
    }

    public Comment to() {
        return new Comment(
            this.getCreatedAt(),
            this.getModifiedAt(),
            this.toChildCommentList(),
            this.getCreateMemberId(),
            this.getContents(),
            this.getProjectId(),
            this.getCommentId(),
            Objects.isNull(this.getParentComment()) ? null : this.getParentComment().to(),
            this.getIsChildComment()
        );
    }

    private List<Comment> toChildCommentList() {

        return Optional.ofNullable(this.childComments)
            .orElseGet(ArrayList::new)
            .stream()
            .map(CommentEntity::to)
            .toList();
    }

    private List<CommentEntity> toChildCommentEntityList(List<Comment> childComments) {

        return Optional.ofNullable(childComments)
            .orElseGet(ArrayList::new)
            .stream()
            .map(CommentEntity::new)
            .toList();
    }
}
