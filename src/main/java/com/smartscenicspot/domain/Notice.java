package com.smartscenicspot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 公告信息类
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/19 18:32
 **/

@Entity
@Table(name = "tb_notice")
public class Notice extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime;

    @NotBlank
    @Column(name = "subject", columnDefinition = "text")
    private String subject;

    /**
     * 0 表示景区公告，1 表示群组公告
     */
    @Column(nullable = false, columnDefinition = "smallint default 0")
    private Byte scope;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private TourGroup tourGroup;

}
