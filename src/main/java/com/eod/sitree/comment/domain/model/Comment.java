package com.eod.sitree.comment.domain.model;

import com.eod.sitree.comment.exception.CommentException;
import com.eod.sitree.common.domain.model.BaseTimeDomain;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Comment extends BaseTimeDomain {

    private final Long commentId;

    private final CommentType commentType;

    private final Long targetId;

    private String contents;

    private final Long createMemberId;

    private final Long parentCommentId;

    private final List<Comment> childComments;

    private final Boolean isChildComment;

    private Boolean isDeleted = false;

    public static String DELETED_COMMENT_CONTENTS = "This comment has been deleted";

    public Comment(LocalDateTime createdAt, LocalDateTime updatedAt, List<Comment> childComments,
        Long createMemberId, Long parentCommentId, String contents, Long targetId, Long commentId,
        Boolean isChildComment, Boolean isDeleted, CommentType commentType) {
        super(createdAt, updatedAt);
        this.childComments = childComments;
        this.createMemberId = createMemberId;
        this.parentCommentId = parentCommentId;
        this.contents = contents;
        this.targetId = targetId;
        this.commentId = commentId;
        this.isChildComment = isChildComment;
        this.isDeleted = isDeleted;
        this.commentType = commentType;
    }

    public Comment(CommentType commentType, Long targetId, String contents, Long parentCommentId, Boolean isChildComment, Member member) {
        super(null, null);
        this.commentId = null;
        this.commentType = commentType;
        this.targetId = targetId;
        this.contents = contents;
        this.createMemberId = member.getMemberId();
        this.parentCommentId = parentCommentId;
        this.isChildComment = isChildComment;
        this.childComments = new ArrayList<>();
        this.isDeleted = false;
    }

    public Comment(Comment comment, Comment parentComment) {
        super(comment.getCreatedAt(), comment.getModifiedAt());
        this.commentType = comment.getCommentType();
        this.commentId = comment.getCommentId();
        this.targetId = comment.getTargetId();
        this.parentCommentId = parentComment.getCommentId();
        this.contents = comment.getContents();
        this.createMemberId = comment.getCreateMemberId();
        this.isChildComment = comment.getIsChildComment();
        this.childComments = comment.getChildComments();
        this.isDeleted = comment.getIsDeleted();
    }

    public void validateParent(Boolean parentCommentExist) {

        if (this.isChildComment && !parentCommentExist) {

            throw new CommentException(ApplicationErrorType.PARENT_COMMENT_NOT_FOUND);
        }

        if (!this.isChildComment && parentCommentExist){

            throw new CommentException(ApplicationErrorType.PARENT_COMMENT_CANNOT_HAVE_PARENT);
        }
    }

    public void updateContents(String contents, Member member) {
        validateCreateMember(member);
        this.contents = contents;
    }

    public void delete(Member member) {
        validateCreateMember(member);
        this.isDeleted = true;
    }

    public void validateCreateMember(Member member) {

        if (!Objects.equals(this.createMemberId, member.getMemberId())) {

            throw new CommentException(ApplicationErrorType.COMMENT_CREATE_MEMBER_NOT_MATCH);
        }
    }
}
