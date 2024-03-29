package com.smartscenicspot.vo;

import com.smartscenicspot.dto.InterestTagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.smartscenicspot.db.pgql.entity.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserVo implements Serializable {
    private Long id;
    private String name;
    private String avatar;
    private String phone;
    private Date birthday;
    private String gender;
    private String address;
    private Double longitude;
    private Double latitude;
    private List<InterestTagDto> interestTags = new ArrayList<>();
}