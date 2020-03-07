package com.zjnu.dormitory.dormitory.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.RoominfoDto;
import com.zjnu.dormitory.dormitory.dto.apply.ApplyRoomDto;
import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.entity.Reserve;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.service.ReserveService;
import com.zjnu.dormitory.dormitory.service.RoominfoService;
import com.zjnu.dormitory.dormitory.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.zjnu.dormitory.dormitory.dto.query.QueryRoom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */

@Api(description = "住房申请")
@RestController
@RequestMapping("/dormitory/roominfo")
@CrossOrigin
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


    //用户查看住房记录
    @Autowired
    UserService userService;
    @Autowired
    ReserveService reserveService;
    private HttpServletResponse response;

    @GetMapping("list")
    @ApiOperation(value = "获取所有消费列表")
    public R list(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
                  @RequestParam(value = "page",defaultValue = "1")Integer page,
                  HttpServletRequest request, HttpServletResponse response,
                  HttpSession session){

//        User user = (User) request.getSession().getAttribute("user");
        //获取用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        Page<RoominfoDto> roominfoDtoPage = new Page<>(page,limit);
        roominfoService.getAllRoomInfo(roominfoDtoPage);
        List<RoominfoDto> roominfoDtoList = roominfoDtoPage.getRecords();

        List temps = new ArrayList();
        for(RoominfoDto roominfod:roominfoDtoList){
            if (roominfod.getUid().equals(user.getUid())){
                int day = Integer.parseInt(roominfod.getDayNum());
                int price = Integer.parseInt(roominfod.getRprice());
                roominfod.setSum(day*price);
                temps.add(roominfod);
            }
        }
        return R.ok().data("data",temps).data("count",temps.size());
    }

//    @ApiOperation(value = "查找用户根据id")
//    @GetMapping("{id}")
//    public R getUserById(@PathVariable String id){
//        User user = userService.getById(id);
//        return R.ok().data("data",user);
//    }


    //管理员查看所有用户住房记录
    @ApiOperation(value = "管理员查看用户记录")
    @GetMapping("admin/list")
    public R adminList(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
                  @RequestParam(value = "page",defaultValue = "1")Integer page){
        Page<RoominfoDto> roominfoDtoPage = new Page<>(page,limit);
        roominfoService.getAllRoomInfo(roominfoDtoPage);
        List<RoominfoDto> roominfoDtoList = roominfoDtoPage.getRecords();
        List temps = new ArrayList();
        for(RoominfoDto roominfod:roominfoDtoList){
            int day = Integer.valueOf(roominfod.getDayNum());
            int price = Integer.valueOf(roominfod.getRprice());
            roominfod.setSum(day*price);
            temps.add(roominfod);
        }
        return R.ok().data("data",temps).data("count",temps.size());
    }

    @ApiOperation(value = "管理员删除记录")
    @DeleteMapping("admin/delete/{id}")
    public R adminDeleteRoomInfo(@PathVariable String id){
        boolean b = reserveService.removeById(id);
        if (b) {
            return R.ok();
        }else{
            return R.error();
        }
    }


    //实现逻辑删除，管理员删除用户住房间记录
    @DeleteMapping("deleteRoom/{id}")
    public boolean DeleteRoomById(@PathVariable("id") String id){
        boolean b = roominfoService.removeById(id);
        return b;
    }

    @ApiOperation(value = "查找空房间并申请")
    @PostMapping("apply")
    public R roomEmpty(@RequestBody @Valid ApplyRoomDto applyRoomDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.error().message(bindingResult.getFieldError().getDefaultMessage());
        }else {
            //获取空房间数量
            Integer count = roominfoService.selectEmtRoomCount(applyRoomDto);
            if (count == 0){
                return R.ok().data("data","抱歉，暂时没有空房间").data("count","0");
            }else {
                //获取空房间
                Roominfo roominfo = roominfoService.selectEmptyRoom(applyRoomDto);

                User user = (User) SecurityUtils.getSubject().getPrincipal();

                //将信息填入reserve
                Reserve reserve = reserveService.acquireReserve(user,applyRoomDto,roominfo);

                reserveService.save(reserve);

                //将被预定的房间设置为非空；
                roominfo.setRstatus("1");
                roominfoService.updateById(roominfo);

                return R.ok().data("data",reserve).data("count","1");
            }

        }
    }

}

