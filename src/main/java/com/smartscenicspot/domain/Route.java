package com.smartscenicspot.domain;

import javax.persistence.*;

/**
 * 静态路线表
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/24 22:57
 **/
@Entity
@Table(name = "tb_route")
public class Route extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)")
    private String name;

    @Column
    private String introduction;

    @Column(columnDefinition = "decimal(10,1) default 5.0")
    private Double hot;

    @Column(columnDefinition = "varchar(100)")
    private String tel;

    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

    // TODO 设计路线表
    //  1. 静态路线直接存 jsonb 格式的所有数据
    //  2. 和景点建立多对多关系，每次查询

}
