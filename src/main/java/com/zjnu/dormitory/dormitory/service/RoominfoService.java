package com.zjnu.dormitory.dormitory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.dto.RoominfoDto;
import com.zjnu.dormitory.dormitory.entity.Roomcost;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

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
}
