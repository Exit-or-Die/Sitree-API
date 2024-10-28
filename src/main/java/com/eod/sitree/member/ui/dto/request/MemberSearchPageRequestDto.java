package com.eod.sitree.member.ui.dto.request;

import com.eod.sitree.common.request.BasePageRequest;
import io.jsonwebtoken.lang.Strings;
import io.micrometer.common.util.StringUtils;
import lombok.Getter;

@Getter
public class MemberSearchPageRequestDto extends BasePageRequest {

    private String q;

    public MemberSearchPageRequestDto(Integer page, Integer size, String q) {
        super(page, size);
        this.q = StringUtils.isEmpty(q) ? Strings.EMPTY : q;
    }

}