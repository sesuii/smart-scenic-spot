package com.smartscenicspot.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 状态码及返回信息响应封装
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/2/27 22:45
 **/

@Data
@AllArgsConstructor
public class Result<T> {
    int code;
    String msg;
    T data;
}
