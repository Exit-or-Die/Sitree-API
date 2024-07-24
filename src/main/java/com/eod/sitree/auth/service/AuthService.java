package com.eod.sitree.auth.service;

import com.eod.sitree.auth.domain.repository.OAuthRepository;
import com.eod.sitree.auth.exception.AuthException;
import com.eod.sitree.auth.exception.AuthSettingException;
import com.eod.sitree.auth.ui.dto.OAuthResponseDto;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Provider;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public static final KeyPair jwtKeypair = generateKeyPair();
    private final OAuthRepository oAuthRepository;

    public void validateOauthToken(Provider provider, String token, String email) {
        ResponseEntity<OAuthResponseDto> response = null;

        if (Provider.GOOGLE.name().equalsIgnoreCase(provider.name())) {

            response = oAuthRepository.validateGoogleToken(token);
        }

        if (Provider.GITHUB.name().equalsIgnoreCase(provider.name())) {

            response = oAuthRepository.validateGithubToken(token);
        }

        if (response == null || !response.getStatusCode().is2xxSuccessful()
            || response.getBody() == null || email.equals(response.getBody().getEmail())) {

            throw new AuthException(ApplicationErrorType.UNAUTHORIZED);
        }
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