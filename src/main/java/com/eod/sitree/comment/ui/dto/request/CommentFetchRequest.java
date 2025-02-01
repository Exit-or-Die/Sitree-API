package com.eod.sitree.comment.ui.dto.request;

import com.eod.sitree.common.request.BasePageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommentFetchRequest extends BasePageRequest {

    public CommentFetchRequest(Integer page, Integer size) {
        super(page, size);
    }
}
