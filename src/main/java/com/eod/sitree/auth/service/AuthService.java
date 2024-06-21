package com.eod.sitree.auth.service;

import com.eod.sitree.auth.domain.MemberClaim;
import com.eod.sitree.common.exception.BadRequestException;
import com.eod.sitree.common.exception.UnauthorizedException;
import com.eod.sitree.auth.ui.dto.SingleTokenDto;
import com.eod.sitree.auth.ui.dto.TokenDto;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;

    @Getter
    private final KeyPair jwtKeypair;

    public AuthService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
        this.jwtKeypair = generateKeyPair();
    }

    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {

            //TODO: 이거 에러나면 인증안됨 -> critical로 등록 필요?
            throw new RuntimeException(e);
        }
    }

}