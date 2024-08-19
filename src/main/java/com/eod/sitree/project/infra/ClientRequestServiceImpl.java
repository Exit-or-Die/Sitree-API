package com.eod.sitree.project.infra;

import com.eod.sitree.project.service.ClientRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientRequestServiceImpl implements ClientRequestService {

    private final RestClient restClient = RestClient.create();

    @Override
    @Cacheable(value = "isHealthy")
    public boolean isHealthy(String healthCheckUrl) {
        long startTime = System.currentTimeMillis();
        try {
            ResponseEntity<Void> response = restClient.get()
                    .uri(healthCheckUrl)
                    .retrieve()
                    .toBodilessEntity();
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) { // Response 가 없는 경우, 및 Request Url 포멧이 올바르지 않은 경우 포괄적인 Exception
            log.info("Health Check Method", e);
            return false;
        }
        finally {
            long stopTime = System.currentTimeMillis();
            long processTime = stopTime - startTime;
            log.info("Health Check Url["+ healthCheckUrl +"] 확인 걸린 시간 : " + processTime + "ms");
        }
    }
}
