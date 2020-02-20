package com.zjnu.dormitory.dormitory.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryRoom {
    @ApiModelProperty(value = "房间号码")
    private String rno;
    @ApiModelProperty(value = "房间类型")
    private String rtype;
    @ApiModelProperty(value = "房间状态")
    private String rstaus;
    @ApiModelProperty(value = "房间价格")
    private String rprice;
}
