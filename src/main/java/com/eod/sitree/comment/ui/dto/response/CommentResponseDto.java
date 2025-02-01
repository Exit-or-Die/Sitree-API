package com.eod.sitree.comment.ui.dto.response;

import com.eod.sitree.comment.domain.model.Comment;
import com.eod.sitree.comment.domain.model.CommentType;
import com.eod.sitree.common.response.BasePageResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {


    private Long commentId;

    private CommentType commentType;

    private Long targetId;

    private String contents;

    private Long createMemberId;

    private Long parentCommentId;

    private List<CommentResponseDto> childComments;

    private Boolean isChildComment;

    private Boolean isDeleted;


    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentType = comment.getCommentType();
        this.targetId = comment.getTargetId();
        this.contents = comment.getContents();
        this.createMemberId = comment.getCreateMemberId();
        this.parentCommentId = comment.getParentCommentId();
        this.childComments = childComments(comment.getChildComments());
        this.isChildComment = comment.getIsChildComment();
        this.isDeleted = comment.getIsDeleted();
    }

    public List<CommentResponseDto> childComments(List<Comment> childComments) {

        return Optional.ofNullable(childComments).orElseGet(ArrayList::new)
            .stream()
            .map(CommentResponseDto::new)
            .toList();
    }
}

