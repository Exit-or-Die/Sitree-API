package com.eod.sitree.comment.ui.dto.response;

import com.eod.sitree.common.response.BasePageResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter

public class CommentsResponseDto extends BasePageResponse<CommentResponseDto> {

    public CommentsResponseDto(Page page) {
        super(page);
    }
}

