package com.eod.sitree.auth.infra;

import com.eod.sitree.auth.domain.repository.OAuthRepository;
import com.eod.sitree.auth.ui.dto.OAuthResponseDto;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class OAuthRepositoryImpl implements OAuthRepository {

    @Value("${oauth.url.google}")
    private String GOOGLE_AUTH_URI;

    @Value("${oauth.url.google}")
    private String GITHUB_AUTH_URI;

    private final RestClient restClient;

    public OAuthRepositoryImpl() {
        this.restClient = RestClient.create();
    }

    @Override
    public ResponseEntity<OAuthResponseDto> validateGoogleToken(String googleToken) {

        return restClient.get()
            .uri(GOOGLE_AUTH_URI + "?access_token=" + googleToken)
            .retrieve()
            .toEntity(OAuthResponseDto.class);
    }

    @Override
    public ResponseEntity<OAuthResponseDto> validateGithubToken(String githubToken) {

        return restClient.get()
            .uri(GITHUB_AUTH_URI)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + githubToken)
            .header(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
            .retrieve()
            .toEntity(OAuthResponseDto.class);
    }
}
