package com.zjnu.dormitory.dormitory.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryUser {
    private String mail;
    private String name;
    private String number;
    private String roleName;
    private String username;

    @Override
    public String toString() {
        return "QueryUser{" +
                "mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", roleName='" + roleName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
