package com.smartscenicspot.db.pgql.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
@EqualsAndHashCode
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String openid;

    @Column(columnDefinition = "varchar(100)")
    private String avatar;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String phone;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(columnDefinition = "varchar(20)")
    private String gender;

    @Column(columnDefinition = "decimal(10,6)")
    private Double longitude;

    @Column(columnDefinition = "decimal(10,6)")
    private Double latitude;

    @Column(columnDefinition = "varchar(100)")
    private String address;

    @Column(name = "status", columnDefinition = "smallint default 0")
    private Byte status;

    @OneToMany(mappedBy = "user")
    private Set<RatingScore> ratingScores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showplace_id")
    @ToString.Exclude
    private Showplace showplace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private TourGroup tourGroup;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<InterestTag> interestTags = new HashSet<>();

}
