package com.eod.sitree.auth.domain.repository;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRepository {

    ResponseEntity<Map> validateGoogleToken(String googleToken);

    ResponseEntity<Map> validateGithubToken(String githubToken);
}
