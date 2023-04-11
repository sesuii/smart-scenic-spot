package com.smartscenicspot.db.pgql.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 公告信息类
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/19 18:32
 **/
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_notice")
public class Notice extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_time")
    private Date publishTime;

    @NotBlank
    @Column(name = "subject", nullable = false, columnDefinition = "varchar(100)")
    private String subject;

    @NotBlank
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "type", columnDefinition = "varchar(20)")
    private String type;

    /**
     * 0 表示景区公告，1 表示群组公告
     */
    @Column(columnDefinition = "smallint default 0")
    private Byte scope;

    @Column(name = "publish_way", columnDefinition = "varchar(50)")
    private String publishWay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private TourGroup tourGroup;

}
