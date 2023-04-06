package com.smartscenicspot.db.pgql.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 景区员工表
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/17 20:43
 **/

@Entity
@Table(name = "tb_staff")
@Data
@NoArgsConstructor
public class Staff extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    @Size(max = 50)
    private String name;

    @Column(name = "status", columnDefinition = "smallint default 1")
    private Byte status;

    @Column(columnDefinition = "varchar(50)")
    private String tel;

    @Column(columnDefinition = "decimal(10,6)")
    private Double longitude;

    @Column(columnDefinition = "decimal(10,6)")
    private Double latitude;

    @Column(columnDefinition = "varchar(100)")
    private String address;

    /**
     * 职责描述
     */
    @Column(columnDefinition = "varchar(100)")
    private String responsibility;

    /**
     * 入职时间
     */
    @Temporal(TemporalType.DATE)
    private Date entryTime;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

}
