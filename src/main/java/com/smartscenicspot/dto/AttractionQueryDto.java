package com.smartscenicspot.dto;

import com.smartscenicspot.db.pgql.entity.Attraction;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link Attraction} entity
 * @author jiahui
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AttractionQueryDto implements Serializable {
    private Long id;
    @NotBlank
    private String name;
    private String category;
    private Byte status;
    private int currentPage = 1;
    private int pageSize = 5;
}