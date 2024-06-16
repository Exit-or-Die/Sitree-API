package com.eod.sitree.member.ui.dto.common;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Getter
public class MemberSignDto {

    private String authId;

    private String email;

    private String nickname;

    private String profileImgUrl;

    public MemberSignDto(String authId, String email, String nickname, String profileImgUrl) {
        this.authId = authId;
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }

    public void validateMemberSignInData() throws BadRequestException {
        if(StringUtils.isEmpty(authId) || StringUtils.isEmpty(email) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(profileImgUrl)){
            throw new BadRequestException();
        }
    }
}
