package com.eod.sitree.member.ui.dto.common;

import com.eod.sitree.member.domain.model.Provider;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberSignDto {

    @NotNull
    private Provider provider;

    @NotNull
    private String oAuthToken;

    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private String profileImgUrl;

    public MemberSignDto(Provider provider, String oAuthToken, String email, String nickname, String profileImgUrl) {
        this.provider = provider;
        this.oAuthToken = oAuthToken;
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }

}
