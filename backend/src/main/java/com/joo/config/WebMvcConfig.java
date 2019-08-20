package com.joo.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final EntityManager entityManager;

    public WebMvcConfig(ApplicationContext applicationContext, EntityManager entityManager) {
        this.applicationContext = applicationContext;
        this.entityManager = entityManager;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //pageable
        PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();
        pageResolver.setOneIndexedParameters(true); //시작 인덱스를 0이 아닌 1로
        pageResolver.setPageParameterName("pageIdx");
        pageResolver.setSizeParameterName("size");
        pageResolver.setFallbackPageable(PageRequest.of(0,10, Sort.Direction.DESC, "idx"));    //default값
        resolvers.add(pageResolver);
    }
}
