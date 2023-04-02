package com.smartscenicspot.pojo;

import lombok.Data;

import javax.persistence.*;

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

    @Column(name = "title", nullable = false, columnDefinition = "varchar(100)")
    private String title;

    @Column(name = "cover_img", nullable = false)
    private String coverImg;

    @Column(columnDefinition = "varchar(100)")
    private String author;

    @Column(columnDefinition = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

}