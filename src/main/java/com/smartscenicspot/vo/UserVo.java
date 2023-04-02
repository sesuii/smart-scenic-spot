package com.smartscenicspot.vo;

import com.smartscenicspot.dto.InterestTagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.smartscenicspot.pojo.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserVo implements Serializable {
    private Long id;
    private String avatar;
    private String phone;
    private Date birthday;
    private String gender;
    private String address;
    private Set<InterestTagDto> interestTags = new HashSet<>();
}