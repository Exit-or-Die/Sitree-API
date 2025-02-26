package com.eod.sitree.common.converter;

import com.eod.sitree.member.domain.model.MyPage;
import com.eod.sitree.project.domain.model.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class MyPageConverter implements AttributeConverter<MyPage, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(MyPage myPage) {
        try {
            return myPage == null ? null : objectMapper.writeValueAsString(myPage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert MyPage to JSON string.", e);
        }
    }

    @Override
    public MyPage convertToEntityAttribute(String s) {
        try {
            return StringUtils.isEmpty(s) ? new MyPage() :  objectMapper.readValue(s, MyPage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert to MyPage.", e);
        }
    }
}
