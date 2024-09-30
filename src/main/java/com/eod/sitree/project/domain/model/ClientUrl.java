package com.eod.sitree.project.domain.model;

import static com.eod.sitree.common.exception.ApplicationErrorType.CHECK_CLIENT_URL_REQUIRED_VALUE;

import com.eod.sitree.project.domain.model.type.PlatformType;
import com.eod.sitree.project.exeption.ProjectException;
import java.util.HashMap;
import lombok.Getter;

@Getter
public class ClientUrl {

    private final HashMap<PlatformType, String> clientUrls;

    public ClientUrl(HashMap<PlatformType, String> clientUrls) {
        validation(clientUrls);
        this.clientUrls = clientUrls;
    }

    public void changeDownloadMethod(PlatformType platformType, String url) {
        this.clientUrls.put(platformType, url);
    }

    public void addDownloadMethod(PlatformType platformType, String url) {
        this.clientUrls.put(platformType, url);
    }

    public void deleteDownloadMethod(PlatformType platformType) {
        this.clientUrls.remove(platformType);
    }

    private void validation(HashMap<PlatformType, String> downloadMethods){
        if (downloadMethods == null || downloadMethods.isEmpty()) {
            throw new ProjectException(CHECK_CLIENT_URL_REQUIRED_VALUE);
        }
    }
}
