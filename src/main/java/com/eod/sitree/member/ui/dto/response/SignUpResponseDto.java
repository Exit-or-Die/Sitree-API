package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.auth.ui.dto.TokenDto;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import lombok.Builder;

public class SignUpResponseDto extends MemberSignDto {

    private Long memberNo;

    private TokenDto tokens;

    public SignUpResponseDto(Member member, TokenDto tokens) {
        super(member.getAuthId(), member.getEmail(), member.getNickname(), member.getProfileImgUrl());
        this.memberNo = member.getMemberNo();
        this.tokens = tokens;
    }

}
