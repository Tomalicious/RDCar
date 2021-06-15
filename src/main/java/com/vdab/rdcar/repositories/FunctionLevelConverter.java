package com.vdab.rdcar.repositories;

import com.vdab.rdcar.domain.FunctionLevels;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class FunctionLevelConverter implements AttributeConverter<FunctionLevels, String> {
    @Override
    public String convertToDatabaseColumn(FunctionLevels functionLevels) {
        return functionLevels.getDisplayName();
    }

    @Override
    public FunctionLevels convertToEntityAttribute(String valueFromDb) {
        return Arrays.stream(FunctionLevels.values()).filter(functionLevels -> functionLevels.getDisplayName().equals(valueFromDb)).findFirst().get();
    }
}
