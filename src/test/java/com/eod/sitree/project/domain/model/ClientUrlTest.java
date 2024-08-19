package com.eod.sitree.project.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.eod.sitree.project.domain.model.type.PlatformType;
import com.eod.sitree.project.exeption.ProjectException;
import java.util.HashMap;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.Platform;

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
        ClientUrl clientUrl = new ClientUrl("liveDomain", downloadMethods);
        assertThat(clientUrl).isNotNull();
    }

    @Test
    void createClientUrlOnlyLiveDomain() {
        ClientUrl liveDomainClientUrl = new ClientUrl("liveDomain", null);
        assertThat(liveDomainClientUrl.getLiveWebDomain()).isNotNull();
        assertThat(liveDomainClientUrl.getDownloadMethods()).isNull();
    }

    @Test
    void createClientUrlOnlyDownloadMethod() {
        ClientUrl liveDomainClientUrl = new ClientUrl(null, downloadMethods);
        assertThat(liveDomainClientUrl.getLiveWebDomain()).isNull();
        assertThat(liveDomainClientUrl.getDownloadMethods()).isNotNull();
    }

    @Test
    void createClientUrlError() {
        Assertions.assertThrows(ProjectException.class, () -> {
            new ClientUrl(null, null);
        });
    }

    @Test
    void changeLiveDomainUrl() {
        String changeLiveDomainUrl = "changed LiveDomainUrl";
        ClientUrl clientUrl = new ClientUrl("liveDomain", downloadMethods);
        clientUrl.changeLiveDomainUrl(changeLiveDomainUrl);
        assertThat(clientUrl.getLiveWebDomain()).isEqualTo(changeLiveDomainUrl);
    }

    @Test
    void changeDownloadMethod() {
        String changeIosDownloadUrl = "This is Changed Live Domain Url";
        ClientUrl clientUrl = new ClientUrl("liveDomain", downloadMethods);
        clientUrl.changeDownloadMethod(PlatformType.IOS, changeIosDownloadUrl);
        assertThat(clientUrl.getDownloadMethods().get(PlatformType.IOS)).isEqualTo(changeIosDownloadUrl);
    }

    @Test
    void addDownloadMethod() {
        ClientUrl clientUrl = new ClientUrl("liveDomain", downloadMethods);
        clientUrl.addDownloadMethod(PlatformType.ANDROID, "new Android DownloadUrl");
        assertThat(clientUrl.getDownloadMethods().size()).isEqualTo(3);
    }
}