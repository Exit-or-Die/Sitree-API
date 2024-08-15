package com.eod.sitree.comment.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateResponseDto {

    private Boolean success;

    public CommentCreateResponseDto(Boolean success) {
        this.success = success;
    }
}
