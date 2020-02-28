package com.zjnu.dormitory.dormitory.dto.basicInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PasswordDto {

//    @NotEmpty(message = "uid..")
//    private String uid;
//    @NotEmpty(message = "name...")
//    private String name;
//    @NotEmpty(message = "username")
//    private String username;

    @NotEmpty(message = "password不能为空")
    private String oldPassword;

    @NotEmpty(message = "newPassword不能为空")
    private String newPassword;

    @NotEmpty(message = "againPassword不能为空")
    private String againPassword;

}
