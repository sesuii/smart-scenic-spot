package com.smartscenicspot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.smartscenicspot.domain.Notice} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoticeDto implements Serializable {
    private Date publishTime;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    private String type;
}