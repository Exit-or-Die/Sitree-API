package com.eod.sitree.comment.ui.dto.request;

import com.eod.sitree.comment.domain.model.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequestDto {

    @NotNull
    private String contents;

    @NotNull
    private Boolean isChildComment;

    private Long parentCommentId;

}
