package com.zjnu.dormitory.dormitory.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.CacheUser;
import com.zjnu.dormitory.dormitory.dto.UserDto;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.service.UserService;

import com.zjnu.dormitory.dormitory.utils.PasswordUtil;
import com.zjnu.dormitory.dormitory.utils.ShiroMd5Util;
import com.zjnu.dormitory.dormitory.utils.VerifyCodeUtils;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import static com.zjnu.dormitory.dormitory.utils.VerifyUtil.RANDOMCODEKEY;

@RestController
@CrossOrigin
@Slf4j
public class LoginController {
    @Autowired
    UserService userService;

    @Resource
    private DefaultKaptcha captchaProducer;

    /**
     *注册验证码图片SessionKey
     */
    public static final String LOGIN_VALIDATE_CODE = "regist_validate_code";
    /**
     * 注册验证码图片
     */
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @RequestMapping(value = {"/registValidateCode"}  ,method = RequestMethod.GET)
    public void registValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VerifyCodeUtils.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE);
    }

    @ApiOperation(value = "管理员登录")
    @PostMapping("admin/login1")
    public R adminLogin(HttpServletRequest request,HttpServletResponse response,
                   @RequestBody UserDto userDto){
//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8088");
        String userName=userDto.getUserName();
        String password=userDto.getPassword();
        String verifyCode=userDto.getVerifyCode();
        if(!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)){


            String serValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
            QueryWrapper<User> wrapper=new QueryWrapper<>();
            wrapper.eq("username",userName);
            User one = userService.getOne(wrapper);
            if(one.getPassword().equals(password)){
                if(serValidateCode.equalsIgnoreCase(verifyCode)){
                    if("admin".equals(one.getRoleName()))
                        return R.ok();
                    else
                        return R.error().message("没有管理员权限!");
                }
                else return R.error().message("验证码错误!");
            }
            else {
                return R.error().message("账号或密码错误！");
            }
        }else {
            return R.error().message("账号或密码错误！");

        }
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("user/login")
    public R userLogin(HttpServletRequest request,HttpServletResponse response,
                   @RequestBody UserDto userDto){
//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8088");
        String userName=userDto.getUserName();
        String password=userDto.getPassword();
        String verifyCode=userDto.getVerifyCode();
        if(!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)){

            String serValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
            QueryWrapper<User> wrapper=new QueryWrapper<>();
            wrapper.eq("username",userName);
            User one = userService.getOne(wrapper);
            if(one.getPassword().equals(password)){
                if(serValidateCode.equalsIgnoreCase(verifyCode))
                    return R.ok();
                else return R.error().message("验证码错误!");
            }
            else {
                return R.error().message("账号或密码错误！1");
            }
        }else {
            return R.error().message("账号或密码错误！2");

        }
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录shiro")
    public R login(@RequestBody UserDto userDto,HttpServletRequest request) {
        log.warn("进入登录.....");
        System.out.println(userDto);
        String userName = userDto.getUserName();
        String password = userDto.getPassword();
        String cliCode = userDto.getVerifyCode();

        if (StringUtils.isEmpty(userName)) {
            return R.error().message("用户名为空！");
        }

        if (StringUtils.isEmpty(password)) {
            return R.error().message("密码为空！");
        }
        if(StringUtils.isEmpty(cliCode)){
            return R.error().message("验证码为空");
        }
        String serValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        System.out.println(request.getSession().getId());
        CacheUser loginUser=null;
        if(serValidateCode.equalsIgnoreCase(cliCode)){
            loginUser = userService.login(userName, password);
        }else{
            return R.error().message("验证码不正确");
        }
        // 登录成功返回用户信息
        return R.ok().data("token",loginUser.getToken());
    }

    @RequestMapping("addUser")
    @ResponseBody
    @ApiOperation("用户注册")
    public R addUser(User user,HttpServletRequest request){
        System.out.println("======addUser=======");
        System.out.println(user.toString());

        //密码加密并set
        user.setPassword(ShiroMd5Util.SysMd5(user));

        boolean addUser_bl = userService.save(user);//将用户数据插入数据库
        if (addUser_bl) {
            return R.ok().message("注册成功!");

        }else{

           return R.error().message("注册失败!");
        }

    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return
     */
    @RequestMapping("/un_auth")
    public R unAuth() {
        return R.error().message("用户未登录！");
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return
     */
    @RequestMapping("/unauthorized")
    public R unauthorized() {
        return R.error().message( "用户无权限！");
    }
}
