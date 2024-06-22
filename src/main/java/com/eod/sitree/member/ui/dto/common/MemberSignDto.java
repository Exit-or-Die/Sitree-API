package com.eod.sitree.member.ui.dto.common;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Getter
public class MemberSignDto {

    @NotNull
    private String authId;

    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private String profileImgUrl;

    public MemberSignDto(String authId, String email, String nickname, String profileImgUrl) {
        this.authId = authId;
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }

}
