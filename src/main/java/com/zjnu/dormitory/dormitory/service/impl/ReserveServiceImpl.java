package com.zjnu.dormitory.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.entity.Reserve;
import com.zjnu.dormitory.dormitory.form.QueryReserve;
import com.zjnu.dormitory.dormitory.mapper.ReserveMapper;
import com.zjnu.dormitory.dormitory.service.ReserveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-13
 */
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve> implements ReserveService {

    @Override
    public void pageList(Page<Reserve> reservePage, QueryReserve queryReserve) {
        if(queryReserve==null){
            baseMapper.selectPage(reservePage,null);
            return;
        }
        QueryWrapper<Reserve>queryWrapper=new QueryWrapper<>();
        Integer dayNum = queryReserve.getDayNum();
        String rno = queryReserve.getRno();
        String username = queryReserve.getName();
        if(dayNum!=null){
            queryWrapper.eq("day_num",dayNum);
        }
        if(!StringUtils.isEmpty(rno)){
            queryWrapper.eq("rno",rno);
        }
        if(!StringUtils.isEmpty(username)){
            queryWrapper.eq("name",username);
        }

        baseMapper.selectPage(reservePage,queryWrapper);
    }
}
