package com.eod.sitree.auth.service;

import com.eod.sitree.auth.exception.AuthSettingException;
import com.eod.sitree.common.exception.ApplicationErrorType;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class AuthService {

    public static final KeyPair jwtKeypair = generateKeyPair();

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