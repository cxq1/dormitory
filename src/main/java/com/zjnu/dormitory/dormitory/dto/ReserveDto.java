package com.zjnu.dormitory.dormitory.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReserveDto {

    private String id;

    private String name;
    private String uid;

    @ApiModelProperty(value = "房间号")
    private String rno;

    @ApiModelProperty(value = "预订日期")
    private Date orderDate;

    @ApiModelProperty(value = "入住时间")
    private Date rInDate;

    @ApiModelProperty(value = "预定天数")
    private Integer dayNum;

    private String reason;

    @ApiModelProperty(value = "预定备注")
    private String note;

    @ApiModelProperty(value = "是否审核 0代表以审核通过1代表未审核")
    private String checked;

    private Date gmtModifyTime;

    @ApiModelProperty(value = "预订时间,创建时间")
    private Date gmtCreatedTime;

    private Roominfo roominfo;
    private User user;
}
