package com.eod.sitree.member.ui;

import com.eod.sitree.auth.support.NoAuthRequired;
import com.eod.sitree.member.service.MemberService;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import com.eod.sitree.member.ui.dto.response.SignInResponseDto;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.ui.dto.response.SignUpResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/member")
public class MemberController {

    private final MemberService memberService;

    @NoAuthRequired
    @PostMapping("/sign-in")
    public ResponseDto<SignInResponseDto> signIn(@Valid MemberSignDto memberSignDto){

        return ResponseDto.ok(memberService.signIn(memberSignDto));
    }

    @NoAuthRequired
    @PostMapping("/sign-up")
    public ResponseDto<SignUpResponseDto> signUp(@Valid MemberSignDto memberSignDto){

        return ResponseDto.ok(memberService.signUp(memberSignDto));
    }

    @NoAuthRequired
    @GetMapping("/refresh")
    public ResponseDto<SignUpResponseDto> refreshToken(){
        //TODO: refresh 로직 구현.
        return null;
    }
}
