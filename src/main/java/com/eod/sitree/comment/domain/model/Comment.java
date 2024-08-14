package com.eod.sitree.comment.domain.model;

import com.eod.sitree.common.domain.model.BaseTimeDomain;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class Comment extends BaseTimeDomain {

    private final Long commentNo;

    private final Long projectId;

    private String contents;

    private final Long createMemberNo;

    private final Comment parentComment;

    private final List<Comment> childComments;

    private final Boolean isChildComment;

    public Comment(LocalDateTime createdAt, LocalDateTime updatedAt, List<Comment> childComments,
        Long createMemberNo, String contents, Long projectId, Long commentNo, Comment parentComment,
        Boolean isChildComment) {
        super(createdAt, updatedAt);
        this.childComments = childComments;
        this.createMemberNo = createMemberNo;
        this.contents = contents;
        this.projectId = projectId;
        this.commentNo = commentNo;
        this.parentComment = parentComment;
        this.isChildComment = isChildComment;
    }
}
