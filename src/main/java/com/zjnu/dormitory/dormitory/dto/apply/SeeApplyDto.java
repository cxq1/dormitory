package com.zjnu.dormitory.dormitory.dto.apply;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class SeeApplyDto {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

//    @ApiModelProperty(value = "房间类型")
//    private String dormitory;

    @ApiModelProperty(value = "入住天数")
    @NotEmpty(message = "入住天数")
    private String dayNum;

    @ApiModelProperty(value = "入住日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rInDate;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "申请理由")
    @NotEmpty(message = "申请理由")
    private String reason;

    private String rno;

    private String rtype;

    private String rprice;

    private String sumPrice;

}
