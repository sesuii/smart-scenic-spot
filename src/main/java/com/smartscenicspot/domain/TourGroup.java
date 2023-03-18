package com.smartscenicspot.domain;

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

    @Column(name = "group_size", columnDefinition = "int4 default 0")
    private Integer groupSize;

    @Column(name = "group_limit_size", columnDefinition = "int4 default 20")
    private Integer groupLimitSize;

    @Column(name = "status", columnDefinition = "smallint default 1")
    private Byte status;

    @OneToOne
    private User creator;

    @OneToMany(mappedBy = "tourGroup")
    private List<User> members;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tourGroup")
    private List<Notice> noticeList;
    @ManyToOne
    @JoinColumn(name = "showplace_id")
    private Showplace showplace;

}
