package com.zjnu.dormitory.dormitory.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnums {
    OK(0 , "成功"),
    ERROR(-1 , "失败")
    ;
    private Integer code;
    private String msg;

    ResultCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
