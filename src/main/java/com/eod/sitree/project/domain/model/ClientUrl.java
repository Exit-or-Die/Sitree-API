package com.eod.sitree.project.domain.model;

import static com.eod.sitree.common.exception.ApplicationErrorType.CHECK_CLIENT_URL_REQUIRED_VALUE;

import com.eod.sitree.project.domain.model.type.PlatformType;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.entity.ClientUrlEntity;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.OverviewDto.ClientUrlDto;
import java.util.HashMap;
import lombok.Getter;

@Getter
public class ClientUrl {

    private String liveWebDomain;
    private final HashMap<PlatformType, String> downloadMethods;

    public ClientUrl(String liveWebDomain, HashMap<PlatformType, String> downloadMethods) {
        validation(liveWebDomain, downloadMethods);
        this.liveWebDomain = liveWebDomain;
        this.downloadMethods = downloadMethods;
    }

    public ClientUrl(ClientUrlEntity entity) {
        this.liveWebDomain = entity.getLiveWebDomain();
        this.downloadMethods = entity.getDownloadMethods();
    }

    public ClientUrl(ClientUrlDto dto) {
        validation(dto.getLiveWebDomain(), dto.getDownloadMethods());
        this.liveWebDomain = dto.getLiveWebDomain();
        this.downloadMethods = dto.getDownloadMethods();
    }

    public void changeLiveDomainUrl(String liveDomainUrl){
        this.liveWebDomain = liveDomainUrl;
    }

    public void changeDownloadMethod(PlatformType platformType, String url) {
        this.downloadMethods.put(platformType, url);
    }

    public void addDownloadMethod(PlatformType platformType, String url) {
        this.downloadMethods.put(platformType, url);
    }

    public void deleteDownloadMethod(PlatformType platformType) {
        this.downloadMethods.remove(platformType);
    }

    private void validation(String liveDomain, HashMap<PlatformType, String> downloadMethods){
        if (liveDomain == null && (downloadMethods == null || downloadMethods.isEmpty())) {
            throw new ProjectException(CHECK_CLIENT_URL_REQUIRED_VALUE);
        }
    }
}