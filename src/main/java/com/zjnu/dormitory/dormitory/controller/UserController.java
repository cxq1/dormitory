package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.form.MdPw;
import com.zjnu.dormitory.dormitory.form.QueryUser;
import com.zjnu.dormitory.dormitory.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@Api(description = "用户功能")
@RestController
@RequestMapping("/dormitory/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "修改密码")
    @PostMapping("mdpw")
    public R updaePw(@Valid @RequestBody MdPw mdPw,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.error().message(bindingResult.getFieldError().getDefaultMessage());
        }else {
            String oldPw=mdPw.getOldPwd();
            String uid = mdPw.getUid();
            String name = mdPw.getName();
            User user= userService.selectUser(uid,oldPw,name);
            if(user!=null){
                user.setPassword(mdPw.getNewPwd());
                boolean b = userService.updateById(user);
                if(b){
                    return R.ok();
                }else {
                    return R.error();
                }
            }else {
                return R.error().message("用户不存在");
            }
        }
    }

    @PostMapping("add")
    @ApiOperation(value = "添加用户")
    public R addUser(@Valid @RequestBody User user,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.error().message(bindingResult.getFieldError().getDefaultMessage());
        }else {
            boolean b = userService.save(user);
            if(b){
                return R.ok();
            }else {
                return R.error();
            }

        }
    }

    @ApiOperation(value = "更新用户")
    @PostMapping("update")
    public R updateUser(@RequestBody @Valid User user, BindingResult bindingResult){
        System.out.println(user);
        if(bindingResult.hasErrors()){
            return R.error().message(bindingResult.getFieldError().getDefaultMessage());
        }else {
            boolean b = userService.updateById(user);
            if(b){
                return R.ok();
            }else {
                return R.error();
            }
        }
    }
    @ApiOperation(value = "根据id获取用户信息")
    @GetMapping("{id}")
    public R getUserById(@PathVariable String id){
        User user = userService.getById(id);
        return R.ok().data("data",user);
    }

    @ApiOperation(value = "用户列表")
    @RequestMapping("list")
    public R getUserList(@RequestParam(name = "limit",defaultValue = "10")Integer limit,
                         @RequestParam(value = "page",defaultValue = "1")Integer page,
                         @RequestParam(name = "name",required = false)String name,
                         @RequestParam(name = "roleName",required = false)String roleName,
                         @RequestParam(name = "number",required = false)String number,
                         @RequestParam(name = "mail",required = false)String mail,
                         HttpServletRequest request){
        QueryUser queryUser= new QueryUser();
        /**
         * 创建搜索条件如果参数存在
         */
        if(!StringUtils.isEmpty(name)){
            queryUser.setName(name);
        }
        if(!StringUtils.isEmpty(roleName)){
            queryUser.setRoleName(roleName);
        }
        if(!StringUtils.isEmpty(number)){
            queryUser.setNumber(number);
        }
        if(!StringUtils.isEmpty(mail)){
            queryUser.setMail(mail);
        }
        System.out.println(request.getSession().getId());
        Page<User>userPage=new Page<>(page,limit);
        userService.pageList(userPage,queryUser);
        List<User> userList = userPage.getRecords();

        return R.ok().data("data",userList).data("count",userPage.getTotal());
    }

    @ApiOperation(value = "删除用户根据id")
    @DeleteMapping("delete/{id}")
    public R deleById(@PathVariable String id){
        boolean b = userService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

