package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.RoomcostDto;
import com.zjnu.dormitory.dormitory.entity.Roomcost;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.service.RoomcostService;
import com.zjnu.dormitory.dormitory.service.RoominfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/dormitory/roomcost")
@CrossOrigin
public class RoomcostController {

    @Autowired
    RoomcostService roomcostService;
//    @Autowired
//    private RoominfoService roominfoService;
//
//    @GetMapping("list")
//    @ApiOperation(value = "获取所有权限列表")
//    public R list(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
//                  @RequestParam(value = "page",defaultValue = "1")Integer page){
//        List<Roomcost> roomcostList = roomcostService.list(null);
//        //创建一个列表存放两个数据库出来的数据
////        List temp = new ArrayList();
////        //通过一个数据库出来结果查找另一个数据库
////        for (Roomcost roomcost : roomcostList){
////            String rno = roomcost.getRno();
////            //创建条件构造器，根据房间号查询房间信息
////            QueryWrapper<Roominfo> queryWrapper = new QueryWrapper<>();
////            queryWrapper.eq("rno",rno);
////            Roominfo roominfo = roominfoService.getOne(queryWrapper);
////            RoomcostDto roomcostDto = new RoomcostDto();
////            BeanUtils.copyProperties(roominfo,roomcostDto);
////            BeanUtils.copyProperties(roomcost,roomcostDto);
////            temp.add(roomcostDto);
////        }
//
////        Page<RoomcostDto> roomcostDtoPage = roomcostService.getRoomCost(new Page<>(page,limit));
//
//        Page<RoomcostDto> roomcostDtoPage = new Page<>(page,limit);
//        roomcostService.getRoomCost(roomcostDtoPage);
//        List<RoomcostDto> roomcostDtos = roomcostDtoPage.getRecords();
//        return R.ok().data("data",roomcostDtos).data("count",roomcostDtoPage.getTotal());
//    }
}

