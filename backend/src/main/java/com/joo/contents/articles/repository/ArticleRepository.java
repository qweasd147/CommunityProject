package com.joo.contents.articles.repository;

import com.joo.common.state.CommonState;
import com.joo.contents.articles.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByIdxAndState(Long articleIdx, CommonState state);

    Page<Article> findAllByState(Pageable pageable, CommonState state);
}
