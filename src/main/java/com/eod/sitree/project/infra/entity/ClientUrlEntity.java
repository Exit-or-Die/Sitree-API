package com.eod.sitree.project.infra.entity;

import com.eod.sitree.common.converter.HashMapConverter;
import com.eod.sitree.project.domain.model.ClientUrl;
import com.eod.sitree.project.domain.model.type.PlatformType;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.MapKeyEnumerated;
import java.util.HashMap;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class ClientUrlEntity {

    private String liveWebDomain;

    @MapKeyEnumerated(EnumType.STRING)
    @Convert(converter = HashMapConverter.class)
    private HashMap<PlatformType, String> downloadMethods;

    public ClientUrlEntity(ClientUrl clientUrl) {
        this.liveWebDomain = clientUrl.getLiveWebDomain();
        this.downloadMethods = clientUrl.getDownloadMethods();
    }
}
