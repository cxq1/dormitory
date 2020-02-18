package com.zjnu.dormitory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjnu.dormitory.dormitory.entity.Roomcost;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.service.RoomcostService;
import com.zjnu.dormitory.dormitory.service.RoominfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class demo {

    @Autowired
    private RoominfoService roominfoService;
    @Autowired
    private RoomcostService roomcostService;

    public void test(){
        List<Roomcost> roomcostList = roomcostService.list(null);
        for (Roomcost roomcost : roomcostList) {
            QueryWrapper<Roominfo>queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("rno",roomcost.getRno());
            Roominfo roominfo = roominfoService.getOne(queryWrapper);
            
        }
    }
}
