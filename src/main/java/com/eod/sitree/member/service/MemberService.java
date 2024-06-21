package com.eod.sitree.member.service;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.auth.ui.dto.TokenDto;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.member.ui.dto.common.MemberSignDto;
import com.eod.sitree.member.ui.dto.response.SignInResponseDto;
import com.eod.sitree.member.ui.dto.response.SignUpResponseDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthService authService;

    public Optional<Member> findMemberByAuthIdAndEmailWithoutException(String authId, String email) {
        return memberRepository.findByAuthIdAndEmailOptional(authId, email);
    }

    public Member findMemberByAuthIdAndEmail(String authId, String email) {
        return memberRepository.findByAuthIdAndEmail(authId, email);
    }

    public SignInResponseDto signIn(MemberSignDto memberSignDto) {
        //TODO: ExceptionHandler 처리
        //memberSignDto.validateMemberSignInData();

        Optional<Member> memberOptional = findMemberByAuthIdAndEmailWithoutException(
            memberSignDto.getAuthId(), memberSignDto.getEmail());

        if (memberOptional.isPresent()) {
            return SignInResponseDto.ofNotNewMember(memberOptional.get());
        }

        return SignInResponseDto.ofNewMember(memberSignDto);
    }

    public SignUpResponseDto signUp(MemberSignDto memberSignDto) {
        //TODO: ExceptionHandler 처리
        //memberSignDto.validateMemberSignInData();

        Optional<Member> memberOptional = findMemberByAuthIdAndEmailWithoutException(
            memberSignDto.getAuthId(), memberSignDto.getEmail());

        if (memberOptional.isPresent()) {
            //TODO: customException 머지시 작업
            //throw new MemberAlreadyExistException();
            throw new RuntimeException("MemberAlreadyExistException");
        }

        Member registeredNewMember = registerNewMember(memberSignDto);
        JwtToken accessToken = new JwtToken(registeredNewMember, JwtTokenType.ACCESS_TOKEN, authService.getJwtKeypair());
        JwtToken refreshToken = new JwtToken(registeredNewMember, JwtTokenType.REFRESH_TOKEN, authService.getJwtKeypair());

        return new SignUpResponseDto(registeredNewMember, new TokenDto(accessToken, refreshToken));
    }

    private Member registerNewMember(MemberSignDto memberSignDto) {
        //TODO: ExceptionHandler 처리
        //memberSignDto.validateMemberSignInData();

        return memberRepository.save(new Member(memberSignDto));
    }

    public SignUpResponseDto refreshToken() {

        //TODO: refresh 로직 구현.
        return null;
    }
}
