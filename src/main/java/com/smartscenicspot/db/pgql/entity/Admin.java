package com.smartscenicspot.db.pgql.entity;

import com.vladmihalcea.hibernate.type.basic.Inet;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLInetType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

/**
 * 景区管理员表
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:04
 **/

@Entity
@Table(name = "tb_admin")
@Data
@TypeDef(
        name = "ipv4",
        typeClass = PostgreSQLInetType.class,
        defaultForType = Inet.class
)
public class Admin extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String avatar;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String account;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String password;

    @Column(columnDefinition = "varchar(50) default 'admin'")
    private String name;

    @Column(columnDefinition = "varchar(10) default 'ADMIN'")
    private String roles;

    @Column(columnDefinition = "varchar(255)")
    private String introduction;

    @Column(name = "last_active_ip", columnDefinition = "inet")
    private Inet lastActiveIp;

    @OneToOne
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

    public void setLastActiveIp(String lastActiveIp) {
        this.lastActiveIp = new Inet(lastActiveIp);
    }

}
