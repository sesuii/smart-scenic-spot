package com.smartscenicspot.db.pgql.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 旅游攻略、文章实体类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/1 21:42
 **/

@Entity
@Table(name = "tb_article")
@Data
public class Article extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, columnDefinition = "varchar(50)")
    private String title;

    @Column(name = "cover_img", columnDefinition = "varchar(255)")
    private String coverImg;

    @Column(columnDefinition = "varchar(100) default 'anonymous'")
    private String author;

    @NotBlank
    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

}