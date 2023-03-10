package com.smartscenicspot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 状态码及返回值信息的枚举值
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/2/27 22:51
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum {

    // 通用状态码及信息
    SUCCESS(200, "SUCCESS"),
    FAILED(500, "服务端异常"),

    AUTHORITY_FAILED(5001, "该用户不存在！"),
    NOT_FOUND(404, "数据不存在");

    private int code;
    private String message;
}
