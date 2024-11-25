package com.eod.sitree.member.service;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.domain.MemberClaim;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import com.eod.sitree.belonging.exception.BelongingException;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.member.exception.MemberException;
import com.eod.sitree.member.ui.dto.request.MemberNicknameExistRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberSearchPageRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberSignInRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberSignUpRequestDto;
import com.eod.sitree.member.ui.dto.request.MemberTokenRequestDto;
import com.eod.sitree.member.ui.dto.response.MemberNicknameExistResponseDto;
import com.eod.sitree.member.ui.dto.response.MemberSearchPageResponse;
import com.eod.sitree.member.ui.dto.response.SignInResponseDto;
import com.eod.sitree.member.ui.dto.response.MemberTokensResponseDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BelongingRepository belongingRepository;
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

//        authService.validateOauthToken(memberSignUpRequestDto.getProvider(),
//            memberSignUpRequestDto.getOAuthToken(), memberSignUpRequestDto.getEmail());

        Optional<Member> memberOptional = memberRepository.findByProviderAndEmailOptional(
            memberSignUpRequestDto.getProvider(), memberSignUpRequestDto.getEmail());

        if (memberOptional.isPresent()) {

            throw new MemberException(ApplicationErrorType.MEMBER_ALREADY_EXIST);
        }

        if (memberSignUpRequestDto.validateBelongingIncluded() && belongingRepository.findById(
            memberSignUpRequestDto.getBelongingId()).isEmpty()) {

            throw new BelongingException(ApplicationErrorType.BELONGING_NOT_FOUND);
        }

        Member registeredNewMember = memberRepository.save(new Member(memberSignUpRequestDto));

        return new MemberTokensResponseDto(registeredNewMember);
    }

    public MemberTokensResponseDto refreshToken(String token) {

        JwtToken refreshToken = new JwtToken(token, AuthService.jwtKeypair);
        refreshToken.validateToken();

        MemberClaim memberClaim = refreshToken.getMemberClaim();
        Member member = memberRepository.findByProviderAndEmail(memberClaim.getProvider(),
            memberClaim.getEmail());

        return new MemberTokensResponseDto(member);
    }

    public MemberTokensResponseDto getToken(MemberTokenRequestDto memberTokenRequestDto) {

        Optional<Member> memberOptional = memberRepository.findByProviderAndEmailOptional(
            memberTokenRequestDto.getProvider(), memberTokenRequestDto.getEmail());

        if (memberOptional.isPresent()) {

            return new MemberTokensResponseDto(memberOptional.get());
        }

        throw new MemberException(ApplicationErrorType.MEMBER_NOT_FOUND);
    }

    public MemberTokensResponseDto getExpiredToken(MemberTokenRequestDto memberTokenRequestDto) {

        Optional<Member> memberOptional = memberRepository.findByProviderAndEmailOptional(
            memberTokenRequestDto.getProvider(), memberTokenRequestDto.getEmail());

        if (memberOptional.isPresent()) {

            return MemberTokensResponseDto.expired(memberOptional.get());
        }

        throw new MemberException(ApplicationErrorType.MEMBER_NOT_FOUND);
    }

    public MemberNicknameExistResponseDto checkExistNickname(
        MemberNicknameExistRequestDto memberNicknameExistRequestDto) {

        return new MemberNicknameExistResponseDto(
            memberRepository.isNicknameExist(memberNicknameExistRequestDto.getNickname()));
    }

    public Member findMember(Provider provider, String email) {

        return memberRepository.findByProviderAndEmail(provider, email);
    }

    public MemberSearchPageResponse searchMembers(MemberSearchPageRequestDto request) {

        return new MemberSearchPageResponse(memberRepository.searchMembers(request.getQ(), request.getPageable()));
    }
}
