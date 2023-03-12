package com.smartscenicspot.domain;

import lombok.*;

import javax.persistence.*;

/**
 * 用户类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/2/27 22:48
 **/

@Entity
@Table(name = "s_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_generator"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String openid;

    private String role;

}
