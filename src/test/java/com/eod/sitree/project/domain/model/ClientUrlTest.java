package com.eod.sitree.project.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.eod.sitree.project.domain.model.type.PlatformType;
import com.eod.sitree.project.exeption.ProjectException;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientUrlTest {

    private HashMap<PlatformType, String> downloadMethods = new HashMap<>();

    @BeforeEach
    void init() {
        // 다운로드 방법 IOS, WINDOW로 초기화
        downloadMethods = new HashMap<>();
        downloadMethods.put(PlatformType.IOS, "ios downloadUrl");
        downloadMethods.put(PlatformType.WINDOWS, "Windows client downloadUrl");
    }

    @Test
    void createClientUrl() {
        ClientUrl clientUrl = new ClientUrl(downloadMethods);
        assertThat(clientUrl).isNotNull();
    }

    @Test
    void createClientUrlOnlyLiveDomain() {
        ClientUrl liveDomainClientUrl = new ClientUrl(null);
        assertThat(liveDomainClientUrl.getClientUrls()).isNull();
    }

    @Test
    void createClientUrlOnlyDownloadMethod() {
        ClientUrl liveDomainClientUrl = new ClientUrl(downloadMethods);
        assertThat(liveDomainClientUrl.getClientUrls()).isNotNull();
    }

    @Test
    void createClientUrlError() {
        Assertions.assertThrows(ProjectException.class, () -> {
            new ClientUrl(null);
        });
    }

    @Test
    void changeDownloadMethod() {
        String changeIosDownloadUrl = "This is Changed Live Domain Url";
        ClientUrl clientUrl = new ClientUrl(downloadMethods);
        clientUrl.changeDownloadMethod(PlatformType.IOS, changeIosDownloadUrl);
        assertThat(clientUrl.getClientUrls().get(PlatformType.IOS)).isEqualTo(changeIosDownloadUrl);
    }

    @Test
    void addDownloadMethod() {
        ClientUrl clientUrl = new ClientUrl(downloadMethods);
        clientUrl.addDownloadMethod(PlatformType.ANDROID, "new Android DownloadUrl");
        assertThat(clientUrl.getClientUrls().size()).isEqualTo(3);
    }
}