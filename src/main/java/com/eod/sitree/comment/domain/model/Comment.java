package com.eod.sitree.comment.domain.model;

import com.eod.sitree.comment.exception.CommentException;
import com.eod.sitree.comment.ui.dto.CommentCreateRequestDto;
import com.eod.sitree.common.domain.model.BaseTimeDomain;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;

@Getter
public class Comment extends BaseTimeDomain {

    private final Long commentId;

    private final Long projectId;

    private String contents;

    private final Long createMemberId;

    private final Long parentCommentId;

    private final List<Comment> childComments;

    private final Boolean isChildComment;

    public Comment(LocalDateTime createdAt, LocalDateTime updatedAt, List<Comment> childComments,
        Long createMemberId, Long parentCommentId, String contents, Long projectId, Long commentId,
        Boolean isChildComment) {
        super(createdAt, updatedAt);
        this.childComments = childComments;
        this.createMemberId = createMemberId;
        this.parentCommentId = parentCommentId;
        this.contents = contents;
        this.projectId = projectId;
        this.commentId = commentId;
        this.isChildComment = isChildComment;
    }

    public Comment(Long projectId, CommentCreateRequestDto request, Member member) {
        super(null, null);
        this.commentId = null;
        this.projectId = projectId;
        this.contents = request.getContents();
        this.createMemberId = member.getMemberId();
        this.parentCommentId = request.getParentCommentId();
        this.isChildComment = request.getIsChildComment();
        this.childComments = new ArrayList<>();
    }

    public Comment(Comment comment, Comment parentComment) {
        super(comment.getCreatedAt(), comment.getModifiedAt());
        this.commentId = comment.getCommentId();
        this.projectId = comment.getProjectId();
        this.parentCommentId = parentComment.getCommentId();
        this.contents = comment.getContents();
        this.createMemberId = comment.getCreateMemberId();
        this.isChildComment = comment.getIsChildComment();
        this.childComments = comment.getChildComments();
    }

    public void validateParent() {

        if (this.isChildComment) {

            throw new CommentException(ApplicationErrorType.COMMENT_NOT_PARENT);
        }
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }

    public void validateCreateMember(Member member) {

        if (!Objects.equals(this.createMemberId, member.getMemberId())) {

            throw new CommentException(ApplicationErrorType.COMMENT_CREATE_MEMBER_NOT_MATCH);
        }
    }
}
