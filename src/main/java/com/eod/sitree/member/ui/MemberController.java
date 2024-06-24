package com.eod.sitree.member.ui;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.domain.MemberClaim;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.member.service.MemberService;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import com.eod.sitree.member.ui.dto.response.SignInResponseDto;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.ui.dto.response.MemberTokensResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    @AuthNotRequired
    @PostMapping("/sign-in")
    public ResponseDto<SignInResponseDto> signIn(@Valid MemberSignDto memberSignDto){

        return ResponseDto.ok(memberService.signIn(memberSignDto));
    }

    @AuthNotRequired
    @PostMapping("/sign-up")
    public ResponseDto<MemberTokensResponseDto> signUp(@Valid MemberSignDto memberSignDto){

        return ResponseDto.ok(memberService.signUp(memberSignDto));
    }

    @AuthNotRequired
    @GetMapping("/refresh")
    public ResponseDto<MemberTokensResponseDto> refreshToken(HttpServletRequest request){

        String token = request.getHeader(JwtTokenType.REFRESH_TOKEN.getHeaderName());

        return ResponseDto.ok(memberService.refreshToken(token));

    }
}
