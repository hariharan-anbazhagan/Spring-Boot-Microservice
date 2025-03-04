package com.hariSolution.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JsonConverter<T> implements AttributeConverter<T, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("JSON conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, (Class<T>) Object.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON conversion error: " + e.getMessage(), e);
        }
    }
}
