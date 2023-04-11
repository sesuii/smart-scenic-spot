package com.smartscenicspot.dto;

import com.smartscenicspot.db.pgql.entity.Showplace;
import com.smartscenicspot.vo.AttractionVo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Showplace} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowplaceDto implements Serializable {
    @NotBlank
    private String name;
    private String address;
    private Double longitude;
    private Double latitude;
    private String imgsUrl;
    private String tels;
    private String introduction;
    private Integer capacity;
    private String openNote;
    private String category;
    private Double score;
    private Byte status;
    private List<AttractionVo> attractions;
}