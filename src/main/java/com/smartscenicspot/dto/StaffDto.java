package com.smartscenicspot.dto;

import com.smartscenicspot.db.pgql.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Staff} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffDto implements Serializable {
    private Long id;
    @Size(max = 50)
    private String name;
    private Byte status;
    private String address;
    private String tel;
    private String responsibility;
    private Date entryTime;
}