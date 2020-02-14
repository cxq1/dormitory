package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.ReserveDto;
import com.zjnu.dormitory.dormitory.entity.Reserve;
import com.zjnu.dormitory.dormitory.entity.Roominfo;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.form.QueryReserve;
import com.zjnu.dormitory.dormitory.service.ReserveService;
import com.zjnu.dormitory.dormitory.service.RoominfoService;
import com.zjnu.dormitory.dormitory.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-13
 */
@Api(description = "申请审核管理")
@RestController
@RequestMapping("/dormitory/reserve")
@CrossOrigin
public class ReserveController {

    @Autowired
    private ReserveService reserveService;
    @Autowired
    private RoominfoService roominfoService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "更新审核")
    @PostMapping("conform")
    public R conformServe(@RequestBody ReserveDto reserveDto){
        Reserve reserve = new Reserve();
        BeanUtils.copyProperties(reserveDto,reserve);
        boolean b = reserveService.updateById(reserve);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id获取审核相关信息")
    @GetMapping("{id}")
    public R getReserveInfo(@PathVariable String id) {
        Reserve reserve = reserveService.getById(id);
        String rno = reserve.getRno();
        String uid = reserve.getUid();
        QueryWrapper<Roominfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rno",rno);
        Roominfo roominfo = roominfoService.getOne(queryWrapper);

        User user = userService.getById(uid);
        ReserveDto reserveDto = new ReserveDto();
        BeanUtils.copyProperties(reserve,reserveDto);
        reserveDto.setRoominfo(roominfo);
        reserveDto.setUser(user);
        return R.ok().data("data",reserveDto);
    }

    @ApiOperation(value = "获取所有审核列表")
    @RequestMapping(value = "list")
    public R getAllList(@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @RequestBody(required = false) QueryReserve queryReserve) {
        Page<Reserve> reservePage = new Page<>(page, limit);
        reserveService.pageList(reservePage, queryReserve);
        List<Reserve> reserveList = reservePage.getRecords();

        return R.ok().data("data", reserveList).data("count", reserveList.size());
    }
}

