package com.zjnu.dormitory.dormitory.controller;


import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.basicInfo.materialDto;
import com.zjnu.dormitory.dormitory.dto.basicInfo.PasswordDto;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.service.UserService;
import com.zjnu.dormitory.dormitory.utils.ShiroMd5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "用户修改基本信息")
@RestController
@RequestMapping("/dormitory/user/basicinfo")
@CrossOrigin
public class UserBasicInfoController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "修改基本信息")
    @PostMapping("update")
    public R updateUser(@RequestBody @Valid materialDto materialDto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.error().message(bindingResult.getFieldError().getDefaultMessage());
        }else {
            System.out.println(materialDto);
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            BeanUtils.copyProperties(materialDto,user);
            boolean b = userService.updateById(user);
            if(b){
                return R.ok().data("data",user);
            }else {
                return R.error();
            }
        }
    }

    @ApiModelProperty(value = "返回数据")
    @GetMapping("rtuser")
    public R rtUser(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String uid = user.getUid();
        User userinfo =  userService.getById(uid);
        return R.ok().data("data",userinfo);
    }



    @ApiOperation(value = "用户修改密码")
    @PostMapping("cgpwd")
    public R updaePw(@Valid @RequestBody PasswordDto passwordDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return R.error().message(bindingResult.getFieldError().getDefaultMessage());
        }else {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            String uid= user.getUid();
//            String username = user.getUsername();
            String oldPw = passwordDto.getOldPassword();
            String name = user.getName();
            User temp =new User(oldPw,name);
            String encodePw = ShiroMd5Util.SysMd5(temp);
            User user2= userService.selectUser(uid,encodePw,name);
            if (user2!=null){
                if (passwordDto.getNewPassword().equals(passwordDto.getAgainPassword())){
                    user2.setPassword(passwordDto.getNewPassword());
                    user2.setPassword(ShiroMd5Util.SysMd5(user2));//加密
                    boolean b = userService.updateById(user2);
                    if(b){
                        return R.ok();
                    }else {
                        return R.error();
                    }
                }else {
                    return R.error().message("两次密码输入不一样");
                }
            }
            else {
                return R.error().message("用户不存在");
            }

        }
    }

}
