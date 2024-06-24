package com.eod.sitree.member.service;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.domain.MemberClaim;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.member.exception.MemberException;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import com.eod.sitree.member.ui.dto.response.SignInResponseDto;
import com.eod.sitree.member.ui.dto.response.MemberTokensResponseDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthService authService;


    public SignInResponseDto signIn(MemberSignDto memberSignDto) {

        Optional<Member> memberOptional = memberRepository.findByAuthIdAndEmailOptional(
            memberSignDto.getAuthId(), memberSignDto.getEmail());

        if (memberOptional.isPresent()) {

            return SignInResponseDto.ofNotNewMember(memberOptional.get());
        }

        return SignInResponseDto.ofNewMember(memberSignDto);
    }

    public MemberTokensResponseDto signUp(MemberSignDto memberSignDto) {

        Optional<Member> memberOptional = memberRepository.findByAuthIdAndEmailOptional(
            memberSignDto.getAuthId(), memberSignDto.getEmail());

        if (memberOptional.isPresent()) {

            throw new MemberException(ApplicationErrorType.MEMBER_ALREADY_EXIST);
        }

        Member registeredNewMember = memberRepository.save(new Member(memberSignDto));

        return new MemberTokensResponseDto(registeredNewMember);
    }

    public MemberTokensResponseDto refreshToken(String token) {

        JwtToken refreshToken = new JwtToken(token, AuthService.jwtKeypair);
        refreshToken.validateToken();

        MemberClaim memberClaim = refreshToken.getMemberClaim();
        Member member = memberRepository.findByAuthIdAndEmail(memberClaim.getAuthId(), memberClaim.getEmail());

        return new MemberTokensResponseDto(member);
    }
}
