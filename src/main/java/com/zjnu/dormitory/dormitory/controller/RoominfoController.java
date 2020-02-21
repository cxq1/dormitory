package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.query.QueryRoom;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.service.RoominfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "住房申请")
@RestController
@CrossOrigin
@RequestMapping("/dormitory/roominfo")
public class RoominfoController {
    @Autowired
    RoominfoService roominfoService;

    @PostMapping("addRoom")
    public R addRoom(@RequestBody(required = false) Roominfo roominfo){
        boolean save = roominfoService.save(roominfo);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //多条件组合并且带分页
    @RequestMapping("moreConditionPageList")
    public R getMoreConditionPageList(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
                                      @RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestBody(required = false) QueryRoom queryRoom){
        Page<Roominfo> pageRoomInfo=new Page<>(page,limit);
        roominfoService.pageListCondition(pageRoomInfo,queryRoom);
        long total = pageRoomInfo.getTotal();
        List<Roominfo> records = pageRoomInfo.getRecords();
        return R.ok().data("count",total).data("data",records);
    }

    //根据id查找
    @PostMapping("queryRoom/{id}")
    public R getRoom(@PathVariable String id){
        Roominfo roominfo = roominfoService.getById(id);
        return R.ok().data("roomInfo",roominfo);
    }


    //根据id修改
    @PostMapping("updateRoom")
    public R updateRoomById(@RequestBody Roominfo roominfo){
        boolean b = roominfoService.updateById(roominfo);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }


    //实现逻辑删除
    @DeleteMapping("deleteRoom/{id}")
    public boolean DeleteRoomById(@PathVariable("id") String id){
        boolean b = roominfoService.removeById(id);
        return b;
    }
}

