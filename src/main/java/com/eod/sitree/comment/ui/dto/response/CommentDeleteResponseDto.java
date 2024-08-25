package com.eod.sitree.comment.ui.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeleteResponseDto {

    private Boolean success;

    public CommentDeleteResponseDto(Boolean success) {
        this.success = success;
    }
}
