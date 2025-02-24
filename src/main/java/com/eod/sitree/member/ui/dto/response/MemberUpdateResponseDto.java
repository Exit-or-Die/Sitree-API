package com.eod.sitree.member.ui.dto.response;

import lombok.Getter;

@Getter
public class MemberUpdateResponseDto {

    private boolean success;

    public MemberUpdateResponseDto(boolean success) {
        this.success = success;
    }
}
