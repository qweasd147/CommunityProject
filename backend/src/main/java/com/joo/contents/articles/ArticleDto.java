package com.joo.contents.articles;

import com.joo.common.domain.WriteInfo;
import com.joo.common.dto.WriteInfoDto;
import com.joo.common.state.CommonState;
import com.joo.common.state.converter.CommonStateConverterImpl;
import com.joo.contents.articles.domain.Article;
import com.joo.contents.articles.domain.Tag;
import com.joo.contents.articles.type.ArticleType;
import com.joo.contents.articles.type.ArticleTypeConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ArticleDto {

    private static Set<String> findTags(String content){

        if(StringUtils.isEmpty(content))    return Collections.EMPTY_SET;

        //Pattern tagPattern = Pattern.compile("\\#([^\\s]*)"); // #부터 다음 공백문자까지 해시태그로 인식
        Pattern tagPattern = Pattern.compile("\\#([^#|\\s]*)"); //#부터 다음 # 또는 공백 문자까지 해시태그로 인식
        Matcher matcher = tagPattern.matcher(content);

        Set<String> tags = new LinkedHashSet<>();

        while(matcher.find()){

            String tag = matcher.group(1);

            if(StringUtils.isEmpty(tag.trim())) continue;

            tags.add(matcher.group(1));
        }

        return tags;
    }

    @Getter
    @NoArgsConstructor
    public static class CreateReq {

        private String subject;
        private String contents;
        private int code;

        @Builder
        public CreateReq(String subject, String contents, int code) {
            this.subject = subject;
            this.contents = contents;
            this.code = code;
        }
        
        public Article toEntity(){

            Set<String> tags = findTags(this.contents);
            ArticleType articleType = ArticleType.findByCode(this.code);

            Article article = Article.builder()
                    .subject(this.subject)
                    .contents(this.contents)
                    .articleType(articleType)
                    .state(CommonState.ENABLE)
                    .build();

            tags.forEach(tagVal -> article.addTag(tagVal));

            return article;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateReq {

        private String subject;
        private String contents;
        private int code;

        @Builder
        public UpdateReq(String subject, String contents, int code) {
            this.subject = subject;
            this.contents = contents;
            this.code = code;
        }

        public ArticleType getArticleType(){
            return ArticleType.findByCode(this.code);
        }

        public Set<String> findTagFromContents(){
            return findTags(this.contents);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ListReq {

        private int size;
        private int page;
        private String desc;

        @Builder
        public ListReq(int size, int page, String desc) {
            this.size = size;
            this.page = page;
            this.desc = desc;
        }

        public Pageable toPageable(){

            List<Sort.Order> orders = new LinkedList<>();
            orders.add(new Sort.Order(Sort.Direction.DESC, "idx"));

            Sort sort = Sort.by(orders);

            PageRequest pageable = PageRequest.of(this.page, this.size, sort);

            return PageRequest.of(this.page, this.size, sort);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Res {

        private Long idx;
        private String subject;
        private String contents;
        private int hits;
        private Set<String> tags = new LinkedHashSet<>();
        private WriteInfoDto writeInfo;

        @Builder
        public Res(Long idx, String subject, String contents, int hits, Set<String> tags, WriteInfoDto writeInfo) {
            this.idx = idx;
            this.subject = subject;
            this.contents = contents;
            this.hits = hits;
            this.tags = tags;
            this.writeInfo = writeInfo;
        }

        public static Res of(Article article){

            LinkedHashSet<String> setTags = article.getTags().stream()
                    .map(tag -> tag.getTag())
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            return Res.builder()
                    .idx(article.getIdx())
                    .subject(article.getSubject())
                    .contents(article.getContents())
                    .hits(article.getHits())
                    .tags(setTags)
                    .writeInfo(WriteInfoDto.of(article.getWriteInfo()))
                    .build();
        }
    }
}
