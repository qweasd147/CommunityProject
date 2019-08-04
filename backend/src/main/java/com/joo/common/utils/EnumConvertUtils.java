package com.joo.common.utils;

import com.joo.common.state.EnumCodeType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.NoSuchElementException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumConvertUtils {

    public static <T extends Enum<T> & EnumCodeType> T getEnumCodeTypeByCode(Class<T> enumClazz, int enumCode){

        return Arrays.stream(enumClazz.getEnumConstants())
            .filter(enumCodeType -> enumCodeType.getCode() == enumCode)
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(
                    String.format("찾을 수 없는 공통 코드. %s in %s", enumCode, enumClazz.getName())));
    }

    public static <T extends Enum<T> & EnumCodeType> int getCodeFromEnumCodeType(T enumCodeType){
        return enumCodeType.getCode();
    }
}
