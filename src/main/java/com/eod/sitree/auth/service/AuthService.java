package com.eod.sitree.auth.service;

import com.eod.sitree.auth.domain.MemberClaim;
import com.eod.sitree.auth.ui.dto.SingleTokenDto;
import com.eod.sitree.auth.ui.dto.TokenDto;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import com.eod.sitree.member.service.MemberService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;

    private final KeyPair jwtKeypair;

    private final String JWT_ISSUER = "EOD";

    private static final Long ACCESS_TOKEN_EXPIRES_IN_MILLISECONDS = 1000L * 60L * 30L;

    private static final Long REFRESH_TOKEN_EXPIRES_IN_MILLISECONDS = 1000L * 60L * 60L * 24L * 7L;

    public AuthService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
        this.jwtKeypair = generateKeyPair();
    }

    public TokenDto createJwtToken(Member member) {
        SingleTokenDto accessToken = createAccessToken(member);
        SingleTokenDto refreshToken = createRefreshToken(member);

        return TokenDto.jwtToken(
            accessToken.getToken(),
            accessToken.getExpiresIn(),
            refreshToken.getToken(),
            refreshToken.getExpiresIn()
        );
    }

    public SingleTokenDto createAccessToken(Member member) {
        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + ACCESS_TOKEN_EXPIRES_IN_MILLISECONDS);
        MemberClaim memberClaim = MemberClaim.from(member);

        String accessToken = Jwts.builder()
            .claim("memberClaim", memberClaim)
            .issuedAt(now)
            .expiration(expiresIn)
            .signWith(jwtKeypair.getPrivate())
            .issuer(JWT_ISSUER)
            .subject(member.getEmail())
            .compact();

        return new SingleTokenDto(accessToken, expiresIn.getTime());
    }

    public SingleTokenDto createRefreshToken(Member member) {
        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + REFRESH_TOKEN_EXPIRES_IN_MILLISECONDS);
        MemberClaim memberClaim = MemberClaim.from(member);

        String refreshToken = Jwts.builder()
            .claim("memberClaim", memberClaim)
            .issuedAt(now)
            .expiration(expiresIn)
            .signWith(jwtKeypair.getPrivate())
            .issuer(JWT_ISSUER)
            .subject(member.getEmail())
            .compact();

        return new SingleTokenDto(refreshToken, expiresIn.getTime());
    }

    public void validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        MemberClaim memberClaim = extractTokenClaim(token);

        Optional<Member> memberOptional = memberRepository.findByAuthIdAndEmailOptional(memberClaim.getAuthId(), memberClaim.getEmail());

        if (memberOptional.isEmpty()) {

            throw new JwtException("Member not found");
        }
    }

    public MemberClaim extractTokenClaim(String jwtToken) {
        try {
            if (StringUtils.isEmpty(jwtToken)) {
                throw new JwtException("JWT token is empty");
            }

            Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(jwtKeypair.getPublic()).build()
                .parseSignedClaims(jwtToken);

            Claims payload = claimsJws.getPayload();
            String authId = payload.get("authId", String.class);
            String email = payload.get("email", String.class);

            return new MemberClaim(authId, email);

            //TODO: exception handler 정의된 후 exception handling 처리 필요
        } catch (JwtException e) {

            //401 UNAUTHORIZED
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {

            //400 BAD REQUEST
            throw new RuntimeException(e);
        } catch (Exception e) {

            //500 INTERNAL SERVER ERROR
            throw new RuntimeException(e);
        }

    }

    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            //TODO: customException 머지시  try/catch 제거
            throw new RuntimeException(e);
        }
    }
}
