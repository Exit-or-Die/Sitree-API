package com.eod.sitree.comment.ui.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequestDto {

    @NotNull
    private Long projectId;

    @NotNull
    private String contents;

    @NotNull
    private Boolean isChildComment;

    private Long parentCommentId;

    public CommentCreateRequestDto(Long projectId, String contents, Boolean isChildComment,
        Long parentCommentId) {
        this.projectId = projectId;
        this.contents = contents;
        this.isChildComment = isChildComment;
        this.parentCommentId = parentCommentId;
    }
}
