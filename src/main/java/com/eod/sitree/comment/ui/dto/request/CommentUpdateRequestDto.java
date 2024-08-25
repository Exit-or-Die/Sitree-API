package com.eod.sitree.comment.ui.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    @NotNull
    private String contents;


    public CommentUpdateRequestDto(String contents) {
        this.contents = contents;
    }
}
