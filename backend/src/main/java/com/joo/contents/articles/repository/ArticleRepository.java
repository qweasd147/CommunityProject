package com.joo.contents.articles.repository;

import com.joo.common.state.CommonState;
import com.joo.contents.articles.domain.Article;
import com.joo.contents.articles.type.ArticleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByIdxAndArticleTypeAndState(Long articleIdx, ArticleType articleType, CommonState state);

    Page<Article> findAllByArticleTypeAndState(Pageable pageable, ArticleType articleType, CommonState state);
}
