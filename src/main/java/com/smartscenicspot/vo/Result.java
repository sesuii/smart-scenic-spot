package com.smartscenicspot.vo;

import com.smartscenicspot.constant.ResultEnum;
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

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result<?> success() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    public static Result<?> success(Object obj) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), obj);
    }

    public static Result<?> failed(ResultEnum resultEnum) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMessage());
    }

    public static Result<?> failed(ResultEnum resultEnum, Object obj) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMessage(), obj);
    }

}
