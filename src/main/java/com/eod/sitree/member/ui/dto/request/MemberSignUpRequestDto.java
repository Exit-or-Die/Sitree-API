package com.eod.sitree.member.ui.dto.request;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.member.domain.model.Provider;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
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

    private String thirdPartyProfileUrl;

    private Long belongingId;


    public MemberSignUpRequestDto(Provider provider, String oAuthToken, String email,
        String thirdPartyProfileUrl, Long belongingId, String nickname) {
        this.provider = provider;
        this.oAuthToken = oAuthToken;
        this.email = email;
        this.thirdPartyProfileUrl = thirdPartyProfileUrl;
        this.belongingId = belongingId;
        this.nickname = nickname;
    }

    public boolean validateBelongingIncluded() {

        return Objects.nonNull(belongingId);
    }
}
