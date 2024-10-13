package com.pbzx.springbootinit.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用返回类
 *
 * @param <T>
 * @author 程序员鱼皮
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
