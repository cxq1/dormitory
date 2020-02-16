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
@ApiModel(value="Roomcost对象", description="")
public class Roomcost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "房间号")
    private String rno;

    @ApiModelProperty(value = "证件号")
    private String number;

    @ApiModelProperty(value = "入住日期")
    private Date indate;

    @ApiModelProperty(value = "离开日期")
    private Date mvdate;

    @ApiModelProperty(value = "房价")
    private String price;

    @ApiModelProperty(value = "预付")
    private String prepay;

    private Date gmtCreatedTime;

    private Date gmtModifyTime;


}
