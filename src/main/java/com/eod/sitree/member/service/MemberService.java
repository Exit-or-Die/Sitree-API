package com.eod.sitree.member.service;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.domain.MemberClaim;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.auth.ui.dto.TokenDto;
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

//    public Optional<Member> findMemberByAuthIdAndEmailWithoutException(String authId, String email) {
//        return memberRepository.findByAuthIdAndEmailOptional(authId, email);
//    }
//
//    public Member findMemberByAuthIdAndEmail(String authId, String email) {
//        return memberRepository.findByAuthIdAndEmail(authId, email);
//    }

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

        return createTokens(registeredNewMember);
    }

    public MemberTokensResponseDto refreshToken(MemberClaim memberClaim) {

        Member member = memberRepository.findByAuthIdAndEmail(memberClaim.getAuthId(),
            memberClaim.getEmail());

        return createTokens(member);
    }

    private MemberTokensResponseDto createTokens(Member member){

        JwtToken accessToken = new JwtToken(member, JwtTokenType.ACCESS_TOKEN, authService.getJwtKeypair());
        JwtToken refreshToken = new JwtToken(member, JwtTokenType.REFRESH_TOKEN, authService.getJwtKeypair());

        return new MemberTokensResponseDto(member, new TokenDto(accessToken, refreshToken));
    }
}
