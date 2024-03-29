package com.smartscenicspot.dto;

import com.smartscenicspot.db.pgql.entity.Admin;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Admin} entity
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