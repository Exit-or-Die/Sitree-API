package com.eod.sitree.comment.ui.dto.response;

import com.eod.sitree.comment.domain.model.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsResponseDto {

    private Long commentId;

    private Long projectId;

    private String contents;

    private Long createMemberId;

    private Long parentCommentId;

    private List<CommentsResponseDto> childComments;

    private Boolean isChildComment;

    private Boolean isDeleted;


    public CommentsResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.projectId = comment.getTargetId();
        this.contents = comment.getContents();
        this.createMemberId = comment.getCreateMemberId();
        this.parentCommentId = comment.getParentCommentId();
        this.childComments = childComments(comment.getChildComments());
        this.isChildComment = comment.getIsChildComment();
        this.isDeleted = comment.getIsDeleted();
    }

    public List<CommentsResponseDto> childComments(List<Comment> childComments) {

        return Optional.ofNullable(childComments).orElseGet(ArrayList::new)
            .stream()
            .map(CommentsResponseDto::new)
            .toList();
    }
}
