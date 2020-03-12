package com.zjnu.dormitory.dormitory.mapper;

import com.zjnu.dormitory.dormitory.dto.apply.SeeApplyDto;
import com.zjnu.dormitory.dormitory.entity.Reserve;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-02-13
 */
public interface ReserveMapper extends BaseMapper<Reserve> {

    @Select("SELECT reserve.* ,roominfo.`rtype`,`rprice` FROM reserve,roominfo WHERE roominfo.rno=reserve.rno && " +
            "reserve.id = ${id}")
    SeeApplyDto getAll(@Param("id") String id);



}
