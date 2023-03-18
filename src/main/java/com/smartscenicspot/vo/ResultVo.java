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
public class ResultVo<T> {
    int code;
    String msg;
    T data;

    public ResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultVo<?> success() {
        return new ResultVo<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    public static ResultVo<?> success(Object obj) {
        return new ResultVo<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), obj);
    }

    public static ResultVo<?> failed(ResultEnum resultEnum) {
        return new ResultVo<>(resultEnum.getCode(), resultEnum.getMessage());
    }

    public static ResultVo<?> failed(ResultEnum resultEnum, Object obj) {
        return new ResultVo<>(resultEnum.getCode(), resultEnum.getMessage(), obj);
    }

}
