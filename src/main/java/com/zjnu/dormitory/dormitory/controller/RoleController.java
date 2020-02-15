package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.RoleDto;
import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.entity.Role;
import com.zjnu.dormitory.dormitory.handler.DorException;
import com.zjnu.dormitory.dormitory.service.PowerService;
import com.zjnu.dormitory.dormitory.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@Api(description = "角色管理")
@RestController
@RequestMapping("/dormitory/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PowerService powerService;

    @ApiOperation(value = "根据id删除role")
    @DeleteMapping("delete/{id}")
    public R deleteById(@PathVariable String id){
        boolean b = roleService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("add")
    public R saveRole(@RequestBody Role role){
        boolean b = roleService.save(role);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "获取角色列表")
    @GetMapping("list")
    public R getRoleList(){
        List<Role> list = roleService.list(null);
        return R.ok().data("data",list);
    }

    /**
     * 只更新角色信息
     * @param roleDto
     * @param bindingResult
     * @return
     *
     */
    @ApiOperation(value = "更新角色信息")
    @PostMapping("/update")
    public R updateRole(@RequestBody @Valid RoleDto roleDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.error().message(bindingResult.getFieldError().getDefaultMessage());
        }else {
            System.out.println(roleDto);
            Role role =new Role();
            BeanUtils.copyProperties(roleDto,role);
            boolean b = roleService.updateById(role);
            if(b){
                return R.ok();
            }else {
                return R.error();
            }

        }
    }

    @ApiOperation(value = "根据id获取role")
    @GetMapping("{id}")
    @CrossOrigin
    public R getRoleById(@PathVariable String id){
        Role role = roleService.getById(id);
//        QueryWrapper<Power>queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("role_name",role.getRoleName());
//        List<String> powerList=null;
//        try {
//            powerList = powerService.list(queryWrapper).stream().map(e->e.getPowerName()).collect(Collectors.toList());
//        }catch (Exception e){
//            return R.error().message("该角色没有对应的power");
//        }

//        RoleDto roleDto =new RoleDto();
//        BeanUtils.copyProperties(role,roleDto);
//        roleDto.setPowers(powerList);
        return R.ok().data("data",role);
    }
}

