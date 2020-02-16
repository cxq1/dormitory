package com.zjnu.dormitory.dormitory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "角色规则")
    @NotEmpty(message = "角色规则不能为空")
    private String roleRule;

    @NotEmpty(message = "角色名不能为空")
    private String roleName;

    @ApiModelProperty(value = "描述")
    private String info;

    @ApiModelProperty(value = "状态")
//    @TableLogic
    private Boolean status;


}
