package com.eod.sitree.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute) {
        return (attribute == null) ? null : attribute.format(formatter);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : LocalDateTime.parse(dbData, formatter);
    }
}
