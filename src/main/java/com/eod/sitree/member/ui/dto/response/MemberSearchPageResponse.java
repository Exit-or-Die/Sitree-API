package com.eod.sitree.member.ui.dto.response;

import com.eod.sitree.common.response.BasePageResponse;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.ui.dto.response.MemberSearchPageResponse.MemberSearchResponseDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class MemberSearchPageResponse extends BasePageResponse<MemberSearchResponseDto> {

    public MemberSearchPageResponse(Page page) {

        super(page);
    }

    @Getter
    public static class MemberSearchResponseDto {

        private Long memberId;

        private String nickname;

        private String email;

        private String profileImgUrl;

        private String thirdPartyProfileUrl;

        private Long belongingId;

        private String belongingName;

        public MemberSearchResponseDto(Long memberId, String nickname,
            String email,
            String profileImgUrl, String thirdPartyProfileUrl, Long belongingId,
            String belongingName) {
            this.memberId = memberId;
            this.nickname = nickname;
            this.email = email;
            this.profileImgUrl = profileImgUrl;
            this.thirdPartyProfileUrl = thirdPartyProfileUrl;
            this.belongingId = belongingId;
            this.belongingName = belongingName;
        }
    }
}
