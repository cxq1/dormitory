package com.zjnu.dormitory.dormitory.dto.apply;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.saxon.trans.SymbolicName.F;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class ApplyRoomDto {

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "房间类型")
    private String dormitory;

    @ApiModelProperty(value = "入住天数")
    @NotEmpty(message = "入住天数")
    private String day;

    @ApiModelProperty(value = "入住日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rInDate;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "申请理由")
    @NotEmpty(message = "申请理由")
    private String reason;

    /**
     * z设置gmtCreatedTime和gmtModifyTime自动更新及策略
     */

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreatedTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModifyTime;
}
