package com.joo.contents.articles.service;

import com.joo.common.state.CommonState;
import com.joo.contents.articles.ArticleDto;
import com.joo.contents.articles.domain.Article;
import com.joo.contents.articles.repository.ArticleRepository;
import com.joo.contents.articles.type.ArticleType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;


@Service
@Slf4j
@AllArgsConstructor
public class ArticleService {

    private ArticleRepository articleRepository;

    public Article create(ArticleDto.CreateReq createReq){
        return articleRepository.save(createReq.toEntity());
    }

    public Page<Article> findAllByDto(ArticleDto.ListReq listReq, ArticleType articleType){

        Pageable pageable = listReq.toPageable();

        return articleRepository.findAllByArticleTypeAndState(pageable, articleType, CommonState.ENABLE);
    }

    public Article update(Long articleIdx, ArticleDto.UpdateReq updateReq){

        Article article = articleRepository.findByIdxAndArticleTypeAndState(articleIdx, updateReq.getArticleType(), CommonState.ENABLE)
                .orElseThrow(() -> new NoSuchElementException("찾을 수 없는 게시물. " + articleIdx));

        article.updateSubject(updateReq.getSubject());
        article.updateContents(updateReq.getContents());

        Set<String> tags = updateReq.findTagFromContents();

        article.getTags().clear();
        tags.forEach(tagVal -> article.addTag(tagVal));

        return article;
    }

    public Article findOne(Long articleIdx, ArticleType articleType){

        return articleRepository.findByIdxAndArticleTypeAndState(articleIdx, articleType, CommonState.ENABLE)
                .orElseThrow(() -> new NoSuchElementException("찾을 수 없는 게시물. " + articleIdx));
    }

    public void delete(Long articleIdx, ArticleType articleType){
        Article article = articleRepository.findByIdxAndArticleTypeAndState(articleIdx, articleType, CommonState.ENABLE)
                .orElseThrow(() -> new NoSuchElementException("찾을 수 없는 게시물. " + articleIdx));

        article.delete();
    }
}
