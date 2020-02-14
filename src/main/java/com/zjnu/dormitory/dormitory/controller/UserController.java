package com.zjnu.dormitory.dormitory.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.form.MdPw;
import com.zjnu.dormitory.dormitory.form.QueryUser;
import com.zjnu.dormitory.dormitory.service.UserService;
import com.zjnu.dormitory.dormitory.utils.VerifyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
    public R getUserList(@RequestParam(name = "limit",required = false)Integer limit,
                         @RequestParam(value = "page",required = false)Integer page,
                         @RequestBody(required = false) QueryUser queryUser){
        System.out.println(queryUser);
        Page<User>userPage=new Page<>(page,limit);
        userService.pageList(userPage,queryUser);
        List<User> userList = userPage.getRecords();

        return R.ok().data("data",userList).data("count",userList.size());
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

