package com.zjnu.dormitory.dormitory.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.apache.ibatis.annotations.ResultMap;

import java.util.Date;

@Data
public class RoominfoDto {
    private String id;

    private String uid;

    @ApiModelProperty(value = "入住时间")
    private Date rinDate;

    @ApiModelProperty(value = "住的天数")
    private String  dayNum;

    @ApiModelProperty(value = "房间类型")
    private String rtype;

    @ApiModelProperty(value = "房价")
    private String rprice;

    @ApiModelProperty(value = "总价钱")
    private Integer sum;

    @ApiModelProperty(value = "房间号")
    private String rno;

    private String name;

}
