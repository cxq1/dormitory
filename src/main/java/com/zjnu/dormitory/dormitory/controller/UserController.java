package com.zjnu.dormitory.dormitory.controller;


import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-08
 */
@RestController
@RequestMapping("/dormitory/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("list")
//    public R getUserList(){
//        List<User> userList = userService.list(null);
//
//    }
}

