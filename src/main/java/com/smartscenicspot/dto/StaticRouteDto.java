package com.smartscenicspot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A DTO for the {@link com.smartscenicspot.db.pgql.entity.Route} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaticRouteDto implements Serializable {
    private Long id;
    private String name;
    @NotBlank
    private String introduction;
    private String coverImg;
    private List<Map<String, String>> roadList;
    private Double hot;
    private String note;
}