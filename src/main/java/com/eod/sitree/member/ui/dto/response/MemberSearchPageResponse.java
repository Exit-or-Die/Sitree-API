package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.common.response.BasePageResponse;
import com.eod.sitree.member.domain.model.Member;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class MemberSearchPageResponse extends BasePageResponse<Member> {

    public MemberSearchPageResponse(Page page) {

        super(page);
    }
}
