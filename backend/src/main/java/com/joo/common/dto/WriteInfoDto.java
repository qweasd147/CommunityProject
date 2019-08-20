package com.joo.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joo.common.domain.WriteInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class WriteInfoDto {

    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    @Builder
    private WriteInfoDto(String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static WriteInfoDto of(WriteInfo writeInfo){

        return WriteInfoDto.builder()
                .createdBy(writeInfo.getCreatedBy().toString())
                .lastModifiedBy(writeInfo.getLastModifiedBy().toString())
                .createdDate(writeInfo.getCreatedDate())
                .lastModifiedDate(writeInfo.getLastModifiedDate())
                .build();
    }
}
