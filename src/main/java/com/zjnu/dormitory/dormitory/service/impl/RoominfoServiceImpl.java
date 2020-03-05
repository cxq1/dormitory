package com.zjnu.dormitory.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.dto.RoominfoDto;
import com.zjnu.dormitory.dormitory.dto.query.QueryRoom;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.mapper.RoominfoMapper;
import com.zjnu.dormitory.dormitory.service.RoominfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Wrapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@Service
public class RoominfoServiceImpl extends ServiceImpl<RoominfoMapper, Roominfo> implements RoominfoService {
//    @Override
//    public Page<RoominfoDto> getRoomInfo(Page<RoominfoDto> page) {
//        return page.setRecords(this.baseMapper.getRoomInfo(page));
//    }

    @Override
    public Page<RoominfoDto> getAllRoomInfo(Page<RoominfoDto> page) {
        return page.setRecords(this.baseMapper.getAllRoomInfo(page));
    }


    public void pageListCondition(Page<Roominfo> roomPage, QueryRoom queryRoom) {
        if(queryRoom==null){
            baseMapper.selectPage(roomPage,null);
            return;
        }
        String rno = queryRoom.getRno();
        String rprice = queryRoom.getRprice();
        String rstaus = queryRoom.getRstaus();
        String rtype = queryRoom.getRtype();
        QueryWrapper<Roominfo> wrapper=new QueryWrapper<>();

        if(!StringUtils.isEmpty(rno)){
            wrapper.like("rno",rno);
        }
        if(!StringUtils.isEmpty(rprice)){
            wrapper.like("rprice",rprice);
        }
        if(!StringUtils.isEmpty(rstaus)){
            wrapper.like("rstatus",rstaus);
        }
        if(!StringUtils.isEmpty(rtype)){
            wrapper.like("rtype",rtype);
        }
        baseMapper.selectPage(roomPage,wrapper);
    }
}
