package com.joo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Slf4j
public class JPAConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public AuditorAware auditorAware(){
        return () -> {
            //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
            //return Optional.of(user.getNickName());
            return Optional.of("temp_id");
        };
    }

    /*
    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).build();

    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager jpaTransactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    */

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}