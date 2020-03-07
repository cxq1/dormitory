package com.zjnu.dormitory.dormitory.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Reserve对象", description="")
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String name;
    private String uid;
    @ApiModelProperty(value = "房间号")
    private String rno;

    @TableField(fill = FieldFill.INSERT)
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

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModifyTime;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "预订时间,创建时间")
    private Date gmtCreatedTime;

}
