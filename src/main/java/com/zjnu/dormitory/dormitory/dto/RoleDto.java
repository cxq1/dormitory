package com.zjnu.dormitory.dormitory.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.zjnu.dormitory.dormitory.entity.Power;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RoleDto {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @NotEmpty
    @ApiModelProperty(value = "角色规则")
    private String roleRule;

    @NotEmpty
    private String roleName;

    @ApiModelProperty(value = "描述")
    private String info;

    @ApiModelProperty(value = "状态")
    private Integer status;

    private List<String>powers;

    @Override
    public String toString() {
        return "RoleDto{" +
                "id='" + id + '\'' +
                ", roleRule='" + roleRule + '\'' +
                ", roleName='" + roleName + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", powers=" + powers +
                '}';
    }
}
