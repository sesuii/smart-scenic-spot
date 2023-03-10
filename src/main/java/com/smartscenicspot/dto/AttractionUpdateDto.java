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
public class AttractionUpdateDto implements Serializable {
    @NotBlank
    private String name;
    @NotBlank
    private String introduction;

    private Byte status;
    private String openNote;
    private double price;
}