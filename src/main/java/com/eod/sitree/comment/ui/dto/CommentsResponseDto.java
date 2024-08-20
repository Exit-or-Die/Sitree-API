package com.eod.sitree.comment.ui.dto;

import com.eod.sitree.comment.domain.model.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsResponseDto {

    private Long commentId;

    private Long projectId;

    private String contents;

    private Long createMemberId;

    private Long parentCommentId;

    private List<CommentsResponseDto> childComments;

    private Boolean isChildComment;

    public CommentsResponseDto(Long commentId, Long projectId, String contents, Long createMemberId,
        Long parentCommentId, List<CommentsResponseDto> childComments,
        Boolean isChildComment) {
        this.commentId = commentId;
        this.projectId = projectId;
        this.contents = contents;
        this.createMemberId = createMemberId;
        this.parentCommentId = parentCommentId;
        this.childComments = childComments;
        this.isChildComment = isChildComment;
    }

    public CommentsResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.projectId = comment.getProjectId();
        this.contents = comment.getContents();
        this.createMemberId = comment.getCreateMemberId();
        this.parentCommentId = comment.getParentCommentId();
        this.childComments = childComments(comment.getChildComments());
        this.isChildComment = comment.getIsChildComment();
    }

    public List<CommentsResponseDto> childComments(List<Comment> childComments) {

        return Optional.ofNullable(childComments).orElseGet(ArrayList::new)
            .stream()
            .map(CommentsResponseDto::new)
            .toList();
    }
}
