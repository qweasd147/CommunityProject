package com.joo.common.state.converter;

import com.joo.common.state.CommonState;

public class CommonStateConverterImpl extends AbstractEnumConverter<CommonState>{

    public CommonStateConverterImpl() {
        this.enumCodeType = CommonState.class;
    }
}