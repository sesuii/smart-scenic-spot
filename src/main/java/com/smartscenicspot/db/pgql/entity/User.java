package com.smartscenicspot.db.pgql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/2/27 22:48
 **/

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "openid", nullable = false, unique = true)
    private String openid;

    @Column(name = "name", columnDefinition = "varchar(50)")
    private String name;

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

    public User(String openid, Byte status) {
        this.openid = openid;
        this.status = status;
    }

    @Column(columnDefinition = "varchar(100)")
    private String address;

    @Column(name = "status", columnDefinition = "smallint default 0")
    private Byte status;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<RatingScore> ratingScores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showplace_id")
    @ToString.Exclude
    private Showplace showplace;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private TourGroup tourGroup;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<InterestTag> interestTags;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Recommendation> recommendationList;
}
