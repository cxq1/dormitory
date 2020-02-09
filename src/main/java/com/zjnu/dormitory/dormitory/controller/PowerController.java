package com.zjnu.dormitory.dormitory.controller;


import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.service.PowerService;
import com.zjnu.dormitory.dormitory.utils.ResultUtils;
import com.zjnu.dormitory.dormitory.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-08
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
}

