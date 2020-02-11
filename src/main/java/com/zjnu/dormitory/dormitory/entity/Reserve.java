package com.zjnu.dormitory.dormitory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="Reserve对象", description="")
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String username;

    @ApiModelProperty(value = "房间号")
    private String rno;

    @ApiModelProperty(value = "预订日期")
    private Date orderDate;

    @ApiModelProperty(value = "入住时间")
    private String rInData;

    @ApiModelProperty(value = "预定天数")
    private Integer dayNum;

    private String reason;

    @ApiModelProperty(value = "预定备注")
    private String note;

    @ApiModelProperty(value = "是否审核")
    private String checked;

    private Date gmtModifyTime;

    @ApiModelProperty(value = "预订时间")
    private Date gmtCreatedTime;


}
