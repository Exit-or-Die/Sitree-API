package com.eod.sitree.comment.ui.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    @NotNull
    private Long projectId;

    @NotNull
    private Long commentId;

    @NotNull
    private String contents;


    public CommentUpdateRequestDto(Long projectId, Long commentId, String contents) {
        this.projectId = projectId;
        this.commentId = commentId;
        this.contents = contents;
    }
}
