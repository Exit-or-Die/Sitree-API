package com.eod.sitree.auth.service;

import com.eod.sitree.auth.domain.MemberClaim;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberService memberService;

    private final SecretKey JWT_SIGNATURE;

    private final String JWT_ISSUER = "EOD";

    private static final Long ACCESS_TOKEN_EXPIRES_IN_MILLISECONDS = 1000L * 60L * 30L;

    private static final Long REFRESH_TOKEN_EXPIRES_IN_MILLISECONDS = 1000L * 60L * 60L * 24L * 7L;

    public AuthService(MemberService memberService, @Value("${jwt.auth.signature}") String jwtSignature) {
        this.memberService = memberService;
        this.JWT_SIGNATURE = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSignature));
    }

    public String createAccessToken(Member member) {
        Date now = new Date();
        MemberClaim memberClaim = MemberClaim.from(member);

        return Jwts.builder()
            .claim("memberClaim", memberClaim)
            .issuedAt(now)
            .expiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRES_IN_MILLISECONDS))
            .signWith(JWT_SIGNATURE)
            .issuer(JWT_ISSUER)
            .compact();
    }

    public String createRefreshToken(Member member) {
        Date now = new Date();
        MemberClaim memberClaim = MemberClaim.from(member);

        return Jwts.builder()
            .claim("memberClaim", memberClaim)
            .issuedAt(now)
            .expiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRES_IN_MILLISECONDS))
            .signWith(JWT_SIGNATURE)
            .issuer(JWT_ISSUER)
            .compact();
    }

    public void validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        MemberClaim memberClaim = extractTokenClaim(token);

        Optional<Member> memberOptional = memberService.findMemberByAuthIdAndEmailWithoutException(
            memberClaim.getAuthId(), memberClaim.getEmail());

        if(memberOptional.isEmpty()){

            throw new JwtException("Member not found");
        }
    }

    public MemberClaim extractTokenClaim(String jwtToken){
        try {
            if(StringUtils.isEmpty(jwtToken)){
                throw new JwtException("JWT token is empty");
            }

            Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(JWT_SIGNATURE).build()
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
        } catch (Exception e){

            //500 INTERNAL SERVER ERROR
            throw new RuntimeException(e);
        }

    }
}
