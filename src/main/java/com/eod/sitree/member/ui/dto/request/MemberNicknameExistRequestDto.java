package com.eod.sitree.member.ui.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberNicknameExistRequestDto {

    @NotNull
    private final String nickname;

}
