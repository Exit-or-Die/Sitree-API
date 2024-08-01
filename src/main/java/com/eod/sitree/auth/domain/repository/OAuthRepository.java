package com.eod.sitree.auth.domain.repository;

import com.eod.sitree.auth.ui.dto.OAuthResponseDto;
import org.springframework.http.ResponseEntity;

public interface OAuthRepository {

    ResponseEntity<OAuthResponseDto> validateGoogleToken(String googleToken);

    ResponseEntity<OAuthResponseDto> validateGithubToken(String githubToken);
}
