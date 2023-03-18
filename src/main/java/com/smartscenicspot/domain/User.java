package com.smartscenicspot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/2/27 22:48
 **/

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@Data
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String openid;

    @Column(columnDefinition = "varchar(100)")
    private String avatar;

    @Column(columnDefinition = "varchar(50)")
    private String phone;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(columnDefinition = "varchar(20)")
    private String gender;

    @Column(columnDefinition = "decimal(10,6)")
    private Double longitude;

    @Column(columnDefinition = "decimal(10,6)")
    private Double latitude;

    private String address;

    @Column(name = "status", columnDefinition = "smallint default 0")
    private Byte status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private TourGroup tourGroup;

}
