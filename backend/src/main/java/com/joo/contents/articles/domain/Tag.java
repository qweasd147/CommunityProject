package com.joo.contents.articles.domain;

import com.joo.common.domain.WriteInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    private String tag;

    @Embedded
    private WriteInfo<String> writeInfo;

    @Builder
    public Tag(Article article, String tag) {
        this.article = article;
        this.tag = tag;
    }
}
