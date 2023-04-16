package com.smartscenicspot.db.pgql.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 群组类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/21 21:40
 **/
@Entity
@Table(name = "tb_tour_group")
@Data
@NoArgsConstructor
public class TourGroup extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "group_name", nullable = false, columnDefinition = "varchar(50)")
    private String groupName;

    @Column(name = "group_intro", columnDefinition = "text")
    private String groupIntro;

    @Column(columnDefinition = "decimal(10,6)")
    private Double longitude;

    @Column(columnDefinition = "decimal(10,6)")
    private Double latitude;

    @Column(name = "group_size", columnDefinition = "int4 default 1", nullable = false)
    private Integer groupSize = 1;

    @Column(name = "group_limit_size", columnDefinition = "int4 default 100")
    private Integer groupLimitSize;

    /**
     * 1表示活跃
     */
    @Column(name = "status", columnDefinition = "smallint default 1")
    private Byte status;

    @Column(name = "invite_code", columnDefinition = "varchar(20)")
    private String inviteCode;

    @OneToOne(cascade = CascadeType.ALL)
    private User creator;

    @OneToMany(mappedBy = "tourGroup", cascade = CascadeType.ALL)
    private List<User> members;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tourGroup", cascade = CascadeType.ALL)
    private List<Notice> noticeList;
    @ManyToOne
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

}
