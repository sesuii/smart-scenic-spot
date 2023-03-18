package com.smartscenicspot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link com.smartscenicspot.domain.Attraction} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttractionDto implements Serializable {
    private Long id;
    @NotBlank
    private String name;
    private String address;
    private String tel;
    private String category;
    private Double score;
    private Integer amrt;
    private String openTime;
    private Long parentId;
}