package com.zjnu.dormitory.dormitory.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MdPw {
    @NotEmpty(message = "uid..")
    private String uid;
    @NotEmpty(message = "name...")
    private String name;
    @NotEmpty(message = "odlpw")
    private String oldPwd;
    @NotEmpty(message = "newpp")
    private String newPwd;

}
