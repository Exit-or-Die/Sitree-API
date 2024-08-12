package com.eod.sitree.member.ui;

import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.auth.support.LocalOnly;
import com.eod.sitree.member.service.MemberService;
import com.eod.sitree.member.ui.dto.request.MemberSignInRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberSignUpRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberTokenRequestDto;
import com.eod.sitree.member.ui.dto.response.SignInResponseDto;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.ui.dto.response.MemberTokensResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @LocalOnly
    @PostMapping("/token")
    public ResponseDto<MemberTokensResponseDto> getToken(@Valid @RequestBody MemberTokenRequestDto memberTokenRequestDto){

        return ResponseDto.ok(memberService.getToken(memberTokenRequestDto));

    }
}
