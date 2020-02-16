package com.zjnu.dormitory.dormitory.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class PowerDto {
    private String id;


    @ApiModelProperty(value = "权限规则")
    @NotEmpty(message = "权限规则不能为空")
    private String powerRule;
    @NotEmpty
    @NotEmpty(message = "权限名不能为空")
    private String powerName;

    @ApiModelProperty(value = "描述")
    private String info;

    @ApiModelProperty(value = "是否可用")
    private Integer status;

    private List<String>roles;
}
