package com.smartscenicspot.db.pgql.pojo;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 静态路线表
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/24 22:57
 **/
@Entity
@Table(name = "tb_route")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Route extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @NotBlank
    @Column(columnDefinition = "text")
    private String introduction;

    @Column(name = "cover_img", columnDefinition = "varchar(255)")
    private String coverImg;

    /**
     * 路线途经点
     */
    @Type(type = "jsonb")
    @Column(name = "road_list", columnDefinition = "jsonb")
    private List<Map<String, String>> roadList;

    @Column(columnDefinition = "decimal(10,1) default 5.0")
    private Double hot;

    @Column(columnDefinition = "varchar(255)")
    private String note;

    @ManyToOne
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

}
