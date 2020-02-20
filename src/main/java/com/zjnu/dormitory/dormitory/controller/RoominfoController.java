package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.RoominfoDto;
import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.service.ReserveService;
import com.zjnu.dormitory.dormitory.service.RoominfoService;
import com.zjnu.dormitory.dormitory.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/dormitory/roominfo")
public class RoominfoController {

    @Autowired
    RoominfoService roominfoService;
    @Autowired
    UserService userService;
    @Autowired
    ReserveService reserveService;

    @GetMapping("list")
    @ApiOperation(value = "获取所有消费列表")
    public R list(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
                  @RequestParam(value = "page",defaultValue = "1")Integer page, HttpServletRequest request){


//        User user = (User) request.getSession().getAttribute("user");


        Page<RoominfoDto> roominfoDtoPage = new Page<>(page,limit);
        roominfoService.getRoomInfo(roominfoDtoPage);
        List<RoominfoDto> roominfoDtoList = roominfoDtoPage.getRecords();

        return R.ok().data("data",roominfoDtoList).data("count",roominfoDtoPage.getTotal());
    }

//    @ApiOperation(value = "查找用户根据id")
//    @GetMapping("{id}")
//    public R getUserById(@PathVariable String id){
//        User user = userService.getById(id);
//        return R.ok().data("data",user);
//    }

    @ApiOperation(value = "管理员查看用户记录")
    @GetMapping("admin/list")
    public R adminList(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
                  @RequestParam(value = "page",defaultValue = "1")Integer page){
        Page<RoominfoDto> roominfoDtoPage = new Page<>(page,limit);
        roominfoService.getAllRoomInfo(roominfoDtoPage);
        List<RoominfoDto> roominfoDtoList = roominfoDtoPage.getRecords();
        return R.ok().data("data",roominfoDtoList).data("count",roominfoDtoPage.getTotal());
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


}

