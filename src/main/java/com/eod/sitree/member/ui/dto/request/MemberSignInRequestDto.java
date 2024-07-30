package com.eod.sitree.member.ui.dto.request;

import com.eod.sitree.member.domain.model.Provider;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberSignInRequestDto {

    @NotNull
    private Provider provider;

    @NotNull
    private String oAuthToken;

    @NotNull
    private String email;

    public MemberSignInRequestDto(Provider provider, String oAuthToken, String email) {
        this.provider = provider;
        this.oAuthToken = oAuthToken;
        this.email = email;
    }
}
