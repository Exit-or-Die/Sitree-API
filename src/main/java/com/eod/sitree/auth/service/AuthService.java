package com.eod.sitree.auth.service;

import com.eod.sitree.member.domain.modelrepository.MemberRepository;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class AuthService {

    private final KeyPair jwtKeypair;

    public AuthService() {
        this.jwtKeypair = generateKeyPair();
    }

    private KeyPair generateKeyPair() {
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