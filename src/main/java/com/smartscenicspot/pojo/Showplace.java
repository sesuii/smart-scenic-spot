package com.smartscenicspot.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 景区信息类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/19 18:37
 **/
@Entity
@Table(name = "tb_showplace")
@Getter
@Setter
@RequiredArgsConstructor
public class Showplace extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(columnDefinition = "varchar(100)")
    private String address;

    @Column(columnDefinition = "decimal(10,6)")
    private Double longitude;

    @Column(columnDefinition = "decimal(10,6)")
    private Double latitude;

    @Column(name = "imgs_url")
    private String imgsUrl;

    @Column(columnDefinition = "varchar(100)")
    private String tels;

    @Column(name = "introduction", columnDefinition = "text")
    private String introduction;

    @Column(columnDefinition = "int4 default 0")
    private Integer capacity;

    @Column(name = "open_note")
    private String openNote;

    @Column(columnDefinition = "varchar(50)")
    private String category;

    @Column(columnDefinition = "decimal(10,2) default 3.5")
    private Double score;

    @Column(columnDefinition = "smallint default 1")
    private Byte status;

    @OneToMany
    @JoinColumn(name = "showplace_id")
    private List<Staff> staffs;

    @OneToMany(mappedBy = "showplace")
    private List<Notice> noticeList;

    @OneToMany(mappedBy = "showplace")
    private List<User> users;

    @OneToMany(mappedBy = "showplace")
    private List<TourGroup> tourGroups;

    @OneToMany(mappedBy = "showplace", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Attraction> attractions;

}
