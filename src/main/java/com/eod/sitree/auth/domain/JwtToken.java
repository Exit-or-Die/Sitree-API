package com.eod.sitree.auth.domain;

import com.eod.sitree.member.domain.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.micrometer.common.util.StringUtils;
import java.security.KeyPair;
import java.util.Date;
import lombok.Getter;

@Getter
public class JwtToken {

    private static final String JWT_ISSUER = "EOD";

    private String tokenValue;
    private KeyPair keyPair;


    public JwtToken(Member member, JwtTokenType jwtTokenType, KeyPair keyPair) {
        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + jwtTokenType.getExpireInMilliSecond());
        MemberClaim memberClaim = new MemberClaim(member);

        this.tokenValue = Jwts.builder()
            .claim("memberClaim", memberClaim)
            .issuedAt(now)
            .expiration(expiresIn)
            .signWith(keyPair.getPrivate())
            .issuer(JWT_ISSUER)
            .subject(member.getEmail())
            .compact();
        this.keyPair = keyPair;
    }

    public JwtToken(String token, KeyPair keyPair) {

        if (StringUtils.isEmpty(token)) {
            throw new JwtException("JWT token is empty");
        }

        this.tokenValue = token;
        this.keyPair = keyPair;
    }

    public void validateToken() {

        Date now = new Date();

        Jws<Claims> claimsJws = Jwts.parser()
            .verifyWith(this.getKeyPair().getPublic()).build()
            .parseSignedClaims(this.tokenValue);

        Claims payload = claimsJws.getPayload();
        Date expiration = payload.getExpiration();

        if (expiration.before(now)) {
            throw new JwtException("JWT token is expired");
        }
    }

    public MemberClaim getMemberClaim() {

        Jws<Claims> claimsJws = Jwts.parser()
            .verifyWith(this.keyPair.getPublic()).build()
            .parseSignedClaims(this.getTokenValue());

        Claims payload = claimsJws.getPayload();
        String authId = payload.get("authId", String.class);
        String email = payload.get("email", String.class);

        return new MemberClaim(authId, email);
    }
}
