package com.zjnu.dormitory.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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


    void pageListCondition(Page<Roominfo> pageRoomInfo, QueryRoom queryRoom);
}
