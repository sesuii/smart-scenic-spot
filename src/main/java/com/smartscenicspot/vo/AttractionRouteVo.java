package com.smartscenicspot.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link com.smartscenicspot.db.pgql.entity.Attraction} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttractionRouteVo implements Serializable {
    private Long id;
    @NotBlank
    private String name;
    private Double longitude;
    private Double latitude;
}