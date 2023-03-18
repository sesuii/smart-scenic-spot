package com.smartscenicspot.domain;

import com.vladmihalcea.hibernate.type.basic.Inet;
import lombok.*;

import javax.persistence.*;

/**
 * 景区管理员表
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:04
 **/

@Entity
@Table(name = "tb_admin")
@NoArgsConstructor
@Data
public class Admin extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String account;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "last_active_ip", columnDefinition = "inet")
    private Inet lastActiveIp;

    @OneToOne
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

}
