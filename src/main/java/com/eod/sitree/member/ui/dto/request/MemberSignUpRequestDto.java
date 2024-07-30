package com.eod.sitree.member.ui.dto.request;

import com.eod.sitree.member.domain.model.Provider;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberSignUpRequestDto {

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

    private String thirdPartyProfileUrl;

    private String belonging;


    public MemberSignUpRequestDto(Provider provider, String oAuthToken, String email,
        String thirdPartyProfileUrl, String belonging, String nickname, String profileImgUrl) {
        this.provider = provider;
        this.oAuthToken = oAuthToken;
        this.email = email;
        this.thirdPartyProfileUrl = thirdPartyProfileUrl;
        this.belonging = belonging;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }
}
