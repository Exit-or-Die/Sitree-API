package com.eod.sitree.comment.domain.model;

import com.eod.sitree.comment.ui.dto.CommentCreateRequestDto;
import com.eod.sitree.common.domain.model.BaseTimeDomain;
import com.eod.sitree.member.domain.model.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Comment extends BaseTimeDomain {

    private final Long commentId;

    private final Long projectId;

    private String contents;

    private final Long createMemberNo;

    private final Comment parentComment;

    private final List<Comment> childComments;

    private final Boolean isChildComment;

    public Comment(LocalDateTime createdAt, LocalDateTime updatedAt, List<Comment> childComments,
        Long createMemberNo, String contents, Long projectId, Long commentId, Comment parentComment,
        Boolean isChildComment) {
        super(createdAt, updatedAt);
        this.childComments = childComments;
        this.createMemberNo = createMemberNo;
        this.contents = contents;
        this.projectId = projectId;
        this.commentId = commentId;
        this.parentComment = parentComment;
        this.isChildComment = isChildComment;
    }

    public Comment(CommentCreateRequestDto request, Member member) {
        super(null, null);
        this.commentId = null;
        this.projectId = request.getProjectId();
        this.parentComment = null;
        this.contents = request.getContents();
        this.createMemberNo = member.getMemberNo();
        this.isChildComment = request.getIsChildComment();
        this.childComments = new ArrayList<>();
    }

    public Comment(Comment comment, Comment parentComment) {
        super(comment.getCreatedAt(), comment.getModifiedAt());
        this.commentId = comment.getCommentId();
        this.projectId = comment.getProjectId();
        this.parentComment = parentComment;
        this.contents = comment.getContents();
        this.createMemberNo = comment.getCreateMemberNo();
        this.isChildComment = comment.getIsChildComment();
        this.childComments = comment.getChildComments();
    }
}
