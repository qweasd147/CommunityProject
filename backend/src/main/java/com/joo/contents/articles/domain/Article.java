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
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
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
    private WriteInfo<String> writeInfo = new WriteInfo<>();

    @Builder
    public Article(@NotNull String subject, @NotNull String contents
            , ArticleType articleType, int hits
            , CommonState state) {

        this.subject = subject;
        this.contents = contents;
        this.articleType = articleType;
        this.hits = hits;
        this.state = state;

        this.tags = new ArrayList<>();
    }

    public void addTag(String tagVal) {

        Tag tag = Tag.builder()
                .tag(tagVal)
                .article(this)
                .build();

        this.tags.add(tag);
    }

    public void updateSubject(String subject) {
        this.subject = subject;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }

    public void changeState(CommonState state) {
        this.state = state;
    }

    public void delete() {
        this.changeState(CommonState.DELETE);
    }
}
