package com.zjnu.dormitory.dormitory.dto.basicInfo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class materialDto {

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "手机")
    @NotEmpty(message = "手机不能为空")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @NotEmpty(message = "邮箱不能为空")
    private String mail;


}
