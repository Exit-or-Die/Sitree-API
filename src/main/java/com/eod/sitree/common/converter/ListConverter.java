package com.eod.sitree.common.converter;

import com.eod.sitree.project.domain.model.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class ListConverter implements AttributeConverter<List<Image>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Image> imageList) {
        try {
            return objectMapper.writeValueAsString(imageList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert List to JSON string.", e);
        }
    }

    @Override
    public List<Image> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<List<Image>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
