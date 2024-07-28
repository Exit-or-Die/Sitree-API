package com.eod.sitree.common.converter;

import com.eod.sitree.project.domain.model.type.PlatformType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;

@Converter
public class HashMapConverter implements AttributeConverter<HashMap<PlatformType, String>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(HashMap map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert map to JSON string.", e);
        }
    }

    @Override
    public HashMap<PlatformType, String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<HashMap<PlatformType, String>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Could not convert JSON string to map.", e);
        }
    }
}
