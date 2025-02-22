package com.eod.sitree.auth.service;

import com.eod.sitree.auth.domain.repository.OAuthRepository;
import com.eod.sitree.auth.exception.AuthException;
import com.eod.sitree.auth.exception.AuthSettingException;
import com.eod.sitree.auth.ui.dto.OAuthResponseDto;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Provider;
import jakarta.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public static final KeyPair jwtKeypair = generateKeyPair();
    private final OAuthRepository oAuthRepository;
    private final Map<Provider, Function<String, ResponseEntity<OAuthResponseDto>>> functionMap = new EnumMap<>(Provider.class);

    @PostConstruct
    public void init() {
        functionMap.put(Provider.GOOGLE, this::validateGoogleToken);
        functionMap.put(Provider.GITHUB, this::validateGithubToken);
    }

    public void validateOauthToken(Provider provider, String token, String email) {
        ResponseEntity<OAuthResponseDto> response = functionMap.get(provider).apply(token);
        validateTokenResponse(email, response);
    }

    private void validateTokenResponse(String email, ResponseEntity<OAuthResponseDto> response) {
        if (response == null || !response.getStatusCode().is2xxSuccessful()
            || response.getBody() == null || !email.equals(response.getBody().getEmail())) {

            throw new AuthException(ApplicationErrorType.OAUTH_UNAUTHORIZED);
        }
    }

    private ResponseEntity<OAuthResponseDto> validateGoogleToken(String token) {

        return oAuthRepository.validateGoogleToken(token);
    }

    private ResponseEntity<OAuthResponseDto> validateGithubToken(String token) {

        return oAuthRepository.validateGithubToken(token);
    }


    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {

            throw new AuthSettingException(ApplicationErrorType.AUTH_KEYPAIR_GENERATION_ERROR);
        }
    }
}