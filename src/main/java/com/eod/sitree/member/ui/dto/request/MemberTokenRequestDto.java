package com.eod.sitree.member.ui.dto.request;

import com.eod.sitree.member.domain.model.Provider;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberTokenRequestDto {

    @NotNull
    private Provider provider;

    @NotNull
    private String email;

    public MemberTokenRequestDto(Provider provider, String email) {
        this.provider = provider;
        this.email = email;
    }

}
