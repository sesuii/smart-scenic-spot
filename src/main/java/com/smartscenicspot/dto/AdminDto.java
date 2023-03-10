package com.smartscenicspot.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.smartscenicspot.domain.Admin} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdminDto implements Serializable {
    private String avatar;
    private String username;
    private String name;
    private List<String> roles;
    private String introduction;
    private String password;
    private String ip;
}