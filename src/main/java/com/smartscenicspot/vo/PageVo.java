package com.smartscenicspot.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/1 0:15
 **/

@Builder
@Data
public class PageVo<T> {
    List<T> data;
    int totalPages;
    long totalElements;
}
