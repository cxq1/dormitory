package com.zjnu.dormitory.dormitory.controller;


import com.zjnu.dormitory.dormitory.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/dormitory/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("test")
    public void test(){
        boolean b = roleService.removeById("2222");
        System.out.println(b);
    }

}

