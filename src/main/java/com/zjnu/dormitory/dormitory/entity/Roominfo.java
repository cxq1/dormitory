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
@ApiModel(value="Roominfo对象", description="")
public class Roominfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "房间号")
    private String rno;

    private String rtype;

    @ApiModelProperty(value = "房间位置")
    private String rposition;

    @ApiModelProperty(value = "房间状态")
    private String rstatus;

    @ApiModelProperty(value = "房间备注")
    private String rnote;

    private String rprice;

    @ApiModelProperty(value = "修改时间")
    private Date gmtCreateTime;

    @ApiModelProperty(value = "创建时间")
    private Date gmtModifyTime;


}
