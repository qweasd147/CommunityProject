package com.joo.contents.articles.type;

import com.joo.common.state.EnumCodeType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum ArticleType implements EnumCodeType {

    type1(0, "type1");

    private int articleCode;
    private String name;

    public static ArticleType findByCode(int articleCode){
        return Arrays.stream(ArticleType.values())
                .filter(articleType -> articleType.getCode() == articleCode)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("알 수 없는 article code. code : "+articleCode));
    }

    @Override
    public int getCode() {
        return this.articleCode;
    }

    @Override
    public String getDescription() {
        return this.name;
    }
}
