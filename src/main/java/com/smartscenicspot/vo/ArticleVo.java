package com.smartscenicspot.vo;

import com.smartscenicspot.db.pgql.pojo.Article;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Article} entity
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
    private String content;
}