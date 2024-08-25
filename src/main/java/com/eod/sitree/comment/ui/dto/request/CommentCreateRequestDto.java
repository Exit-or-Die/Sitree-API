package com.eod.sitree.comment.ui.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequestDto {

    @NotNull
    private String contents;

    @NotNull
    private Boolean isChildComment;

    private Long parentCommentId;


    public CommentCreateRequestDto(String contents, Boolean isChildComment, Long parentCommentId) {
        this.contents = contents;
        this.isChildComment = isChildComment;
        this.parentCommentId = parentCommentId;
    }
}
