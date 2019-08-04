package com.joo.contents.articles.type;

import com.joo.common.state.converter.AbstractEnumConverter;

public class ArticleTypeConverter extends AbstractEnumConverter<ArticleType>{

    public ArticleTypeConverter() {
        this.enumCodeType = ArticleType.class;
    }
}