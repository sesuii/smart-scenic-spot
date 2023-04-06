package com.smartscenicspot.db.pgql.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 用户推荐表
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/8 18:54
 **/

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_recommendation")
public class Recommendation extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

    @Column(name = "propensity", columnDefinition = "decimal(10, 2)")
    private Double propensity;

    /**
     * 0 表示用户未去过，1 表示用户已经去过
     */
    @Column(name = "status", columnDefinition = "smallint default 0")
    private Byte status;
}
