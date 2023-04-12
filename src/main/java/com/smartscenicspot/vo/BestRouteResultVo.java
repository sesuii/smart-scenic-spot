package com.smartscenicspot.vo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 多目标哈密顿通路结果
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/12 19:28
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BestRouteResultVo implements Serializable {
    List<Long> viaNodeIds;
    Double totalCost;
}
