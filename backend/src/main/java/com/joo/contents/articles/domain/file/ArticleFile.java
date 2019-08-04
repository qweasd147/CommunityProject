package com.joo.contents.articles.domain.file;

import com.joo.common.domain.WriteInfo;
import com.joo.contents.articles.domain.Article;
import com.joo.contents.file.domain.FileBasic;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Embedded
    private FileBasic fileBasic;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @Embedded
    private WriteInfo<String> writeInfo;

    @Builder
    public ArticleFile(FileBasic fileBasic, Article article) {
        this.fileBasic = fileBasic;
        this.article = article;
    }

    public void delete(){
        fileBasic.delete();
    }
}
