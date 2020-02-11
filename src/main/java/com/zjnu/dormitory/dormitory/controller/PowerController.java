package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.service.PowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@Api(description = "权限管理")
@RestController
@RequestMapping("/dormitory/power")
@CrossOrigin
public class PowerController {
    @Autowired
    private PowerService powerService;

    @GetMapping("list")
    @ApiOperation(value = "获取所有权限列表")
    public R list(){
        List<Power> powerList = powerService.list(null);
        return R.ok().data("data",powerList).data("count",powerList.size());

    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取具体权限")
    public R getById(@PathVariable String id){
        Power power = powerService.getById(id);
        return R.ok().data("data",power);
    }

    @PostMapping("updatePower")
    @ApiOperation(value = "根据id更新具体权限")
    public R updateByid(@RequestBody Power power){
        boolean b = powerService.updateById(power);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("delete/{id}")
    public R deleteByid(@PathVariable String id){
        boolean b = powerService.removeById(id);
        QueryWrapper<Power>wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        powerService.remove(wrapper);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @ApiOperation(value = "添加权限")
    @PostMapping("add")
    public R addPower(@RequestBody Power power){
        boolean b = powerService.save(power);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
}



