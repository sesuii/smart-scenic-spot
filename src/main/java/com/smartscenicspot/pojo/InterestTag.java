package com.smartscenicspot.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户兴趣标签表
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/2 14:17
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_interest_tag")
public class InterestTag extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name", columnDefinition = "varchar(20)")
    private String tagName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_user_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    private Set<User> users;

}
