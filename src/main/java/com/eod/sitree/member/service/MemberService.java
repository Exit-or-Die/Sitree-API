package com.eod.sitree.member.service;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.MemberClaim;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.member.exception.MemberException;
import com.eod.sitree.member.ui.dto.request.MemberSignInRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberSignUpRequestDto;
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


    public SignInResponseDto signIn(MemberSignInRequestDto memberSignInRequestDto) {

        authService.validateOauthToken(memberSignInRequestDto.getProvider(),
            memberSignInRequestDto.getOAuthToken(), memberSignInRequestDto.getEmail());

        Optional<Member> memberOptional = memberRepository.findByProviderAndEmailOptional(
            memberSignInRequestDto.getProvider(), memberSignInRequestDto.getEmail());

        if (memberOptional.isPresent()) {

            return SignInResponseDto.ofNotNewMember(memberOptional.get());
        }

        return SignInResponseDto.ofNewMember(memberSignInRequestDto);
    }

    public MemberTokensResponseDto signUp(MemberSignUpRequestDto memberSignUpRequestDto) {

        authService.validateOauthToken(memberSignUpRequestDto.getProvider(),
            memberSignUpRequestDto.getOAuthToken(), memberSignUpRequestDto.getEmail());

        Optional<Member> memberOptional = memberRepository.findByProviderAndEmailOptional(
            memberSignUpRequestDto.getProvider(), memberSignUpRequestDto.getEmail());

        if (memberOptional.isPresent()) {

            throw new MemberException(ApplicationErrorType.MEMBER_ALREADY_EXIST);
        }

        Member registeredNewMember = memberRepository.save(new Member(memberSignUpRequestDto));

        return new MemberTokensResponseDto(registeredNewMember);
    }

    public MemberTokensResponseDto refreshToken(String token) {

        JwtToken refreshToken = new JwtToken(token, AuthService.jwtKeypair);
        refreshToken.validateToken();

        MemberClaim memberClaim = refreshToken.getMemberClaim();
        Member member = memberRepository.findByProviderAndEmail(memberClaim.getProvider(), memberClaim.getEmail());

        return new MemberTokensResponseDto(member);
    }
}
