package com.zjnu.dormitory.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.dto.RoominfoDto;
import com.zjnu.dormitory.dormitory.dto.apply.ApplyRoomDto;
import com.zjnu.dormitory.dormitory.entity.Reserve;
import com.zjnu.dormitory.dormitory.dto.query.QueryRoom;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
public interface RoominfoService extends IService<Roominfo> {
//    Page<RoominfoDto> getRoomInfo(@PathVariable Page<RoominfoDto> page);

    Page<RoominfoDto> getAllRoomInfo(Page<RoominfoDto> page);

    void pageListCondition(Page<Roominfo> pageRoomInfo, QueryRoom queryRoom);

    //看是否存在空房间
    Integer selectEmtRoomCount(ApplyRoomDto applyRoomDto);
    //获取一个空房间信息
    Roominfo selectEmptyRoom(ApplyRoomDto applyRoomDto);

}
