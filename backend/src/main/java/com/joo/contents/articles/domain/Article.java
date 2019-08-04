package com.joo.contents.articles.domain;

import com.joo.common.domain.WriteInfo;
import com.joo.common.state.CommonState;
import com.joo.common.state.converter.CommonStateConverterImpl;
import com.joo.contents.articles.type.ArticleType;
import com.joo.contents.articles.type.ArticleTypeConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    @NotNull
    private String subject;

    @Column(nullable = false)
    @NotNull
    @Lob
    private String contents;

    @Convert(converter = ArticleTypeConverter.class)
    private ArticleType articleType;

    private int hits;

    @Convert(converter = CommonStateConverterImpl.class)
    private CommonState state = CommonState.ENABLE;

    @OrderBy("idx ASC ")
    @BatchSize(size = 50)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "article")
    private List<Tag> tags = new ArrayList<>();

    @Embedded
    private WriteInfo<String> writeInfo;

    @Builder
    public Article(@NotNull String subject, @NotNull String contents, int hits, CommonState state, List<Tag> tags) {
        this.subject = subject;
        this.contents = contents;
        this.hits = hits;
        this.state = state;
        this.tags = tags;
    }
}
