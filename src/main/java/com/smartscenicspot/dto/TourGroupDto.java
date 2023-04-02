package com.smartscenicspot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link com.smartscenicspot.pojo.TourGroup} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourGroupDto implements Serializable {
    private Long id;
    @NotBlank
    private String groupName;
    private String groupIntro;
    private Double longitude;
    private Double latitude;
    private Integer groupSize;
    private Byte status;
    private String inviteCode;
}