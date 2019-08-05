package com.joo.contents.articles.service;

import com.joo.common.state.CommonState;
import com.joo.contents.articles.ArticleDto;
import com.joo.contents.articles.domain.Article;
import com.joo.contents.articles.repository.ArticleRepository;
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

    public Page<Article> findAllByDto(ArticleDto.ListReq listReq){

        Pageable pageable = listReq.toPageable();

        return articleRepository.findAllByState(pageable, CommonState.ENABLE);
    }

    public Article update(Long articleIdx, ArticleDto.UpdateReq updateReq){

        Article article = articleRepository.findByIdxAndState(articleIdx, CommonState.ENABLE)
                .orElseThrow(() -> new NoSuchElementException("찾을 수 없는 게시물. " + articleIdx));

        article.updateSubject(updateReq.getSubject());
        article.updateContents(updateReq.getContents());

        Set<String> tags = updateReq.findTagFromContents();

        article.getTags().clear();
        tags.forEach(tagVal -> article.addTag(tagVal));

        return article;
    }

    public Article findOne(Long articleIdx){

        return articleRepository.findByIdxAndState(articleIdx, CommonState.ENABLE)
                .orElseThrow(() -> new NoSuchElementException("찾을 수 없는 게시물. " + articleIdx));
    }

    public void delete(Long articleIdx){
        Article article = articleRepository.findByIdxAndState(articleIdx, CommonState.ENABLE)
                .orElseThrow(() -> new NoSuchElementException("찾을 수 없는 게시물. " + articleIdx));

        article.delete();
    }
}
