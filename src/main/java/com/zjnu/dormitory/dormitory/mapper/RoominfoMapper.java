package com.zjnu.dormitory.dormitory.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.dto.RoominfoDto;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
public interface RoominfoMapper extends BaseMapper<Roominfo> {
    //可删
//    @Select("SELECT roominfo.`rno`,`rtype`,`rprice`,reserve.`r_in_date`,reserve.`uid`,reserve.`id`,`day_num` FROM " +
//            "roominfo,reserve" + " " +
//            "WHERE " +
//            "roominfo.rno=reserve.rno && reserve.checked=1")
////    @Select("SELECT roominfo.`rno`,`rtype`")
//    List<RoominfoDto> getRoomInfo(@PathVariable Page<RoominfoDto> page);

//    管理员查看所有用户入住信息
    @Select("SELECT roominfo.`rno`,`rtype`,`rprice`,reserve.`r_in_date`,`day_num`,reserve.`uid`,reserve.`id`,`name`,`checked` " +
            "FROM roominfo,reserve " +
            " WHERE " +
            "roominfo.rno=reserve.rno " +
            "ORDER BY r_in_date DESC")
    List<RoominfoDto> getAllRoomInfo(Page<RoominfoDto> page);
}
