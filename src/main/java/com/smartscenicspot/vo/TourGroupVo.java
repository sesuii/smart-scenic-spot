package com.smartscenicspot.vo;

import com.smartscenicspot.dto.NoticeDto;
import com.smartscenicspot.db.pgql.entity.TourGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link TourGroup} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourGroupVo implements Serializable {
    private Date gmtCreate;
    private Long id;
    @NotBlank
    private String groupName;
    private String groupIntro;
    private Double longitude;
    private Double latitude;
    private Integer groupSize;
    private Byte status;
    private UserVo creator;
    private List<UserVo> members;
    private List<NoticeDto> noticeList;
    private String inviteCode;
}