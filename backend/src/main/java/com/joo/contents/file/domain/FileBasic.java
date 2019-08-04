package com.joo.contents.file.domain;

import com.joo.common.state.CommonState;
import com.joo.common.state.converter.CommonStateConverterImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class FileBasic {

    private String contentType;
    private String filePath;
    private String originFileName;
    private String saveFileName;
    private long fileSize;
    @Convert(converter = CommonStateConverterImpl.class)
    private CommonState state = CommonState.ENABLE;

    public void delete(){
        changeState(CommonState.DELETE);
    }

    public void changeState(CommonState state){
        this.state = state;
    }
}
