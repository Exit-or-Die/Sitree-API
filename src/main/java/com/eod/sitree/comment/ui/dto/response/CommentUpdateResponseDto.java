package com.eod.sitree.comment.ui.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateResponseDto {

    private Boolean success;

    public CommentUpdateResponseDto(Boolean success) {
        this.success = success;
    }
}
