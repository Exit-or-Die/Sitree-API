package com.eod.sitree.member.ui;

import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.auth.support.LocalOnly;
import com.eod.sitree.member.service.MemberService;
import com.eod.sitree.member.ui.dto.request.MemberNicknameExistRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberSearchPageRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberSignInRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberSignUpRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberTokenRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberUpdateRequestDto;
import com.eod.sitree.member.ui.dto.response.MemberNicknameExistResponseDto;
import com.eod.sitree.member.ui.dto.response.MemberSearchPageResponse;
import com.eod.sitree.member.ui.dto.response.MemberUpdateResponseDto;
import com.eod.sitree.member.ui.dto.response.SignInResponseDto;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.ui.dto.response.MemberTokensResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @AuthNotRequired
    @PostMapping("/sign-in")
    public ResponseDto<SignInResponseDto> signIn(@Valid @RequestBody MemberSignInRequestDto memberSignInRequestDto){

        return ResponseDto.ok(memberService.signIn(memberSignInRequestDto));
    }

    @AuthNotRequired
    @PostMapping("/sign-up")
    public ResponseDto<MemberTokensResponseDto> signUp(@Valid @RequestBody MemberSignUpRequestDto memberSignUpRequestDto){

        return ResponseDto.ok(memberService.signUp(memberSignUpRequestDto));
    }

    @AuthNotRequired
    @GetMapping("/refresh")
    public ResponseDto<MemberTokensResponseDto> refreshToken(HttpServletRequest request){

        String token = request.getHeader(JwtTokenType.REFRESH_TOKEN.getHeaderName());

        return ResponseDto.ok(memberService.refreshToken(token));
    }

    @AuthNotRequired
    @GetMapping("/nickname/exist")
    public ResponseDto<MemberNicknameExistResponseDto> checkExistNickname(@Valid MemberNicknameExistRequestDto memberNicknameExistRequestDto){

        return ResponseDto.ok(memberService.checkExistNickname(memberNicknameExistRequestDto));
    }

    @AuthNotRequired
    @LocalOnly
    @PostMapping("/token")
    public ResponseDto<MemberTokensResponseDto> getToken(@Valid @RequestBody MemberTokenRequestDto memberTokenRequestDto){

        return ResponseDto.ok(memberService.getToken(memberTokenRequestDto));
    }

    // TODO: live시 제거 필요
    @AuthNotRequired
    @PostMapping("/token/expired")
    public ResponseDto<MemberTokensResponseDto> getExpiredToken(@Valid @RequestBody MemberTokenRequestDto memberTokenRequestDto){

        return ResponseDto.ok(memberService.getExpiredToken(memberTokenRequestDto));
    }

    // @AuthNotRequired
    @GetMapping("/search")
    public ResponseDto<MemberSearchPageResponse> searchMembers(MemberSearchPageRequestDto request) {

        return ResponseDto.ok(memberService.searchMembers(request));
    }

    @PutMapping("/members/{memberId}")
    public ResponseDto<MemberUpdateResponseDto> updateMember(@PathVariable Long memberId, @Valid @RequestBody MemberUpdateRequestDto request) {

        return ResponseDto.ok(memberService.updateMember(memberId, request));
    }
}
