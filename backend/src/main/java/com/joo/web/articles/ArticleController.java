package com.joo.web.articles;

import com.joo.contents.articles.ArticleDto;
import com.joo.contents.articles.domain.Article;
import com.joo.contents.articles.service.ArticleService;
import com.joo.contents.articles.type.ArticleType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("/{type}/article")
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, Object> searchArticleList(ArticleDto.ListReq listReq, @PathVariable ArticleType type){

        Page<ArticleDto.Res> resPage = articleService.findAllByDto(listReq, type)
                .map(ArticleDto.Res::of);

        Map<String, Object> listData = new HashMap<>();

        listData.put("listData", resPage.getContent());
        listData.put("count", resPage.getTotalElements());    //전체 데이터 수
        listData.put("page", resPage.getNumber()+1);          //현재 페이지
        listData.put("isLast", resPage.isLast());

        return listData;
    }

    @GetMapping("/{type}/article/{articleIdx}")
    @ResponseStatus(value = HttpStatus.OK)
    public ArticleDto.Res searchArticleOne(@PathVariable Long articleIdx, @PathVariable ArticleType type){

        Article article = articleService.findOne(articleIdx, type);

        return ArticleDto.Res.of(article);
    }

    @PostMapping("/{type}/article")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ArticleDto.Res createArticleOne(@RequestBody ArticleDto.CreateReq createReq, @PathVariable ArticleType type){

        Article article = articleService.create(createReq);

        return ArticleDto.Res.of(article);
    }

    @PutMapping("/{type}/article/{articleIdx}")
    @ResponseStatus(value = HttpStatus.OK)
    public ArticleDto.Res modifyArticleOne(ArticleDto.UpdateReq updateReq, @PathVariable Long articleIdx){

        Article article = articleService.update(articleIdx, updateReq);

        return ArticleDto.Res.of(article);
    }

    @DeleteMapping("/{type}/article/{articleIdx}")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeArticleOne(@PathVariable Long articleIdx, @PathVariable ArticleType type){
        articleService.delete(articleIdx, type);
    }
}
