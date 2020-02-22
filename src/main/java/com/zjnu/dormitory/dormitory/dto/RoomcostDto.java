package com.zjnu.dormitory.dormitory.dto;

import com.zjnu.dormitory.dormitory.entity.Roomcost;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RoomcostDto {
    private String id;
    @ApiModelProperty(value = "入住时间")
    private Date indate;
    @ApiModelProperty(value = "离开时间")
    private Date mvdate;
    @ApiModelProperty(value = "房间类型")
    private String rtype;
    @ApiModelProperty(value = "房价")
    private String rprice;

    private String prepay;
    private String rno;


//    private Roominfo roominfo;
//    private Roomcost roomcost;
}
