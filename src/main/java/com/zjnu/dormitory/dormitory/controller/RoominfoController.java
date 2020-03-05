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
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.zjnu.dormitory.dormitory.dto.query.QueryRoom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        User user=(User) SecurityUtils.getSubject().getPrincipal();
        String roleName = user.getRoleName();
        if(roleName.equals("admin")){
            boolean save = roominfoService.save(roominfo);
            if(save){
                return R.ok();
            }else {
                return R.error();
            }
        }else{
            return R.error();
        }

    }

    //多条件组合并且带分页
    @RequestMapping("moreConditionPageList")
    public R getMoreConditionPageList(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
                                      @RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(name = "rno",required = false)String rno,
                                      @RequestParam(name = "rprice",required = false)String rprice,
                                      @RequestParam(name = "rtype",required = false)String rtype,
                                      @RequestParam(name = "rstatus",required = false)String rstatus,
                                      HttpServletRequest request){
        QueryRoom queryRoom = new QueryRoom();
        if(!StringUtils.isEmpty(rtype)){
            queryRoom.setRtype(rtype);
        }
        if(!StringUtils.isEmpty(rprice)){
            queryRoom.setRprice(rprice);
        }
        if(!StringUtils.isEmpty(rno)){
            queryRoom.setRno(rno);
        }
        if(!StringUtils.isEmpty(rstatus)){
            queryRoom.setRstaus(rstatus);
        }
        Page<Roominfo> roomPage=new Page<>(page,limit);
        roominfoService.pageListCondition(roomPage,queryRoom);
        List<Roominfo> records = roomPage.getRecords();
        return  R.ok().data("data",records).data("count",roomPage.getTotal());
    }

    //根据id查找
    @GetMapping("queryRoom/{id}")
    public R getRoom(@PathVariable String id){
        Roominfo roominfo = roominfoService.getById(id);
        return R.ok().data("data",roominfo);
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
    private HttpServletResponse response;

    @GetMapping("list")
    @ApiOperation(value = "获取所有消费列表")
    public R list(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
                  @RequestParam(value = "page",defaultValue = "1")Integer page,
                  HttpServletRequest request, HttpServletResponse response,
                  HttpSession session){

//        User user = (User) request.getSession().getAttribute("user");
//        System.out.println(session.getId());
//        System.out.println("***********************************************");
        Page<RoominfoDto> roominfoDtoPage = new Page<>(page,limit);
        roominfoService.getAllRoomInfo(roominfoDtoPage);
        List<RoominfoDto> roominfoDtoList = roominfoDtoPage.getRecords();

        List temps = new ArrayList();
        for(RoominfoDto roominfod:roominfoDtoList){
            int day = Integer.parseInt(roominfod.getDayNum());
            int price = Integer.parseInt(roominfod.getRprice());
            roominfod.setSum(day*price);
            temps.add(roominfod);
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
        boolean b = roominfoService.removeById(id);
        if (b) {
            return R.ok();
        }else{
            return R.error();
        }
    }


    //实现逻辑删除，管理员删除用户住房间记录
    @DeleteMapping("logicDelete/{id}")
    public R DeleteRoomById(@PathVariable("id") String id){
        Roominfo room = roominfoService.getById(id);
        room.setLogicDelete(1);
        boolean b = roominfoService.updateById(room);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }
}

