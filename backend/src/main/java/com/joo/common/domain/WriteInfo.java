package com.joo.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
public class WriteInfo<T> {

    @Column(nullable = false, updatable = false)
    @CreatedBy
    private T createdBy;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private T lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
