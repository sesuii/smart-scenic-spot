package com.smartscenicspot.vo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.smartscenicspot.pojo.Article} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArticleVo implements Serializable {
    private Long id;
    private String title;
    private Date publishTime;
    private String coverImg;
    private String author;
    private String text;
}