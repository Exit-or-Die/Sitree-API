package com.eod.sitree.member.ui;

import com.eod.sitree.auth.support.NoAuthRequired;
import com.eod.sitree.member.service.MemberService;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import com.eod.sitree.member.ui.dto.response.SignInResponseDto;
import com.eod.sitree.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @NoAuthRequired
    @PostMapping("/sign-in")
    public ResponseDto<SignInResponseDto> signIn(MemberSignDto memberSignDto){

        return ResponseDto.ok(memberService.signIn(memberSignDto));
    }
}
