package com.smartscenicspot.dto;

import com.smartscenicspot.db.pgql.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Notice} entity
 * @author jiahui
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoticeDto implements Serializable {
    private long id;
    private Date publishTime;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    private String type;
    private String publishWay = "人工推送";
    private Byte scope = 0;
}