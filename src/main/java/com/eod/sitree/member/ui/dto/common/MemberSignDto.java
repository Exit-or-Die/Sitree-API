package com.eod.sitree.member.ui.dto.common;

import com.eod.sitree.member.domain.model.Provider;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Getter
public class MemberSignDto {

    @NotNull
    private Provider provider;

    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private String profileImgUrl;

    public MemberSignDto(Provider provider, String email, String nickname, String profileImgUrl) {
        this.provider = provider;
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }

}
