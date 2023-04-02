package com.smartscenicspot.pojo;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 景点信息类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/21 21:51
 **/
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_attraction")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Attraction extends  AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "address", nullable = false, columnDefinition = "varchar(100)")
    private String address;

    @Column(columnDefinition = "decimal(10,6)")
    private Double longitude;

    @Column(columnDefinition = "decimal(10,6)")
    private Double latitude;

    @Column(name = "imgs_url")
    private String imgsUrl;

    @NotBlank
    @Column(name = "introduction", columnDefinition = "text")
    private String introduction;

    @Column(name = "tel", columnDefinition = "varchar(50)")
    private String tel;

    @Column(name = "category", columnDefinition = "varchar(50)")
    private String category;

    /**
     * 景点基础设施
     */
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Map<String, String>> infrastructure;

    @Column(name = "score", columnDefinition = "decimal(10,2) default 3.5")
    private Double score;

    @Column(name = "price", columnDefinition = "decimal(10,2) default 0")
    private double price;

    @Column(columnDefinition = "int4 default 0")
    private Integer capacity;
    /**
     * 游客平均驻留时间
     */
    @Column(name = "amrt", columnDefinition = "int4 default 0")
    private Integer amrt;

    @Column(name = "status", columnDefinition = "smallint default 1")
    private Byte status;

    @Column(name = "open_note")
    private String openNote;

    @OneToMany(mappedBy = "attraction")
    @ToString.Exclude
    private List<Staff> staffs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @ToString.Exclude
    private Showplace showplace;

}
