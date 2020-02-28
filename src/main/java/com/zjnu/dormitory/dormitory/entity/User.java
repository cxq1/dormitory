package com.zjnu.dormitory.dormitory.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.ID_WORKER_STR)
    private String uid;

    @ApiModelProperty(value = "真实姓名")
    @NotEmpty(message = "真实姓名")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String password;

    private String username;

    private String sex;
    @NotEmpty(message = "不能为空")
    private String phone;

    private String mail;

    private String number;

    private String roleName;

    @ApiModelProperty(value = "是否可用")
    private Integer status;

    @ApiModelProperty(value = "盐值")
    private String salt;

    /**
     * z设置gmtCreatedTime和gmtModifyTime自动更新及策略
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreatedTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModifyTime;

    public User(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public User() {
    }
}
