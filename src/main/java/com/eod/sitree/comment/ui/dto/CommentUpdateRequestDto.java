package com.eod.sitree.comment.ui.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    @NotNull
    private Long commentId;

    @NotNull
    private String contents;


    public CommentUpdateRequestDto(Long commentId, String contents) {
        this.commentId = commentId;
        this.contents = contents;
    }
}
