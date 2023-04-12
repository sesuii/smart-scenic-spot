package com.smartscenicspot.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 实时路线 Query
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/13 0:49
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteQueryVo implements Serializable {
    private Double longitude;
    private Double latitude;
    List<Long> target;
}
