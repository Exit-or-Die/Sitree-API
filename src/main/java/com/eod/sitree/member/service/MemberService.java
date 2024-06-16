package com.eod.sitree.member.service;

import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findMemberByAuthIdAndEmailWithoutException(String authId, String email) {
        return memberRepository.findByAuthIdAndEmailOptional(authId, email);
    };
}
