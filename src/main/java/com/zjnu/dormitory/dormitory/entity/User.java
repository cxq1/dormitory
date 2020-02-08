package com.zjnu.dormitory.dormitory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2020-02-08
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
    private String name;

    @ApiModelProperty(value = "昵称")
    private String username;

    private String password;

    private String sex;

    private String phone;

    private String mail;

    private String number;

    private String roleName;

    @ApiModelProperty(value = "是否可用")
    private Integer status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private String salt;

    private Date gmtCreatedTime;

    private Date gmtModifyTime;


}
