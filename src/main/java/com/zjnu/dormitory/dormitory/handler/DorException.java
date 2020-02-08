package com.zjnu.dormitory.dormitory.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DorException extends RuntimeException {
    private Integer code;
    private String message;
}
