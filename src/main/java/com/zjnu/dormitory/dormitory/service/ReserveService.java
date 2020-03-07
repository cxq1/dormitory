package com.zjnu.dormitory.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.dto.apply.ApplyRoomDto;
import com.zjnu.dormitory.dormitory.entity.Reserve;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.form.QueryReserve;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-13
 */
public interface ReserveService extends IService<Reserve> {

    void pageList(Page<Reserve> reservePage, QueryReserve queryReserve);

    //填充一个reserve信息
    Reserve acquireReserve(User user, ApplyRoomDto applyRoomDto, Roominfo roominfo);
}
