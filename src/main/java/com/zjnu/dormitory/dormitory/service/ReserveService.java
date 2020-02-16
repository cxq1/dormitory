package com.zjnu.dormitory.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.entity.Reserve;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
