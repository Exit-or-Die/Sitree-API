package com.eod.sitree.project.infra.entity;

import com.eod.sitree.common.converter.HashMapConverter;
import com.eod.sitree.project.domain.model.type.PlatformType;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.MapKeyEnumerated;
import java.util.HashMap;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ClientUrlEntity {

    private String liveWebDomain;
    @MapKeyEnumerated(EnumType.STRING)
    @Convert(converter = HashMapConverter.class)
    private final HashMap<PlatformType, String> downloadMethods = new HashMap<>();
}
