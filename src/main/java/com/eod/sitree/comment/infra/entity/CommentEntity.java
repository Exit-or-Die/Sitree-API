package com.eod.sitree.comment.infra.entity;

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
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    @Length(min = 1, max = 1000)
    private String contents;

    @Column(nullable = false)
    private Long createMemberNo;

    @ManyToOne
    @JoinColumn(name = "parent_comment_no")
    private CommentEntity parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CommentEntity> childComments;

    @Column(nullable = false)
    private Boolean isChildComment;

    public CommentEntity(Long commentNo, Long projectId, String contents, Long createMemberNo,
        CommentEntity parentComment, List<CommentEntity> childComments, Boolean isChildComment) {
        this.commentNo = commentNo;
        this.projectId = projectId;
        this.contents = contents;
        this.createMemberNo = createMemberNo;
        this.parentComment = parentComment;
        this.childComments = childComments;
        this.isChildComment = isChildComment;
    }
}
