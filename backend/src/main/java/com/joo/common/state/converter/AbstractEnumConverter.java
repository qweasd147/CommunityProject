package com.joo.common.state.converter;

import com.joo.common.state.EnumCodeType;
import com.joo.common.utils.EnumConvertUtils;

import javax.persistence.AttributeConverter;

public abstract class AbstractEnumConverter<T extends Enum<T> & EnumCodeType> implements AttributeConverter<T, Integer> {

    protected Class<T> enumCodeType;

    @Override
    public Integer convertToDatabaseColumn(T attribute) {
        return EnumConvertUtils.getCodeFromEnumCodeType(attribute);
    }

    @Override
    public T convertToEntityAttribute(Integer dbData) {
        return EnumConvertUtils.getEnumCodeTypeByCode(enumCodeType, dbData);
    }
}
