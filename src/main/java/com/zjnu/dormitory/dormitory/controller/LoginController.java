package com.zjnu.dormitory.dormitory.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.UserDto;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.service.UserService;
import com.zjnu.dormitory.dormitory.utils.CodeUtils;
import com.zjnu.dormitory.dormitory.utils.VerifyUtil;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.java.Log;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.zjnu.dormitory.dormitory.utils.VerifyUtil.RANDOMCODEKEY;

@RestController
@CrossOrigin
@Logger
public class LoginController {
    @Autowired
    UserService userService;
    @ApiOperation(value = "用户登录")
    @PostMapping("/login1")
    public R login(HttpServletRequest request, @RequestBody UserDto userDto){
        if(!StringUtils.isEmpty(userDto.getUserName()) && !StringUtils.isEmpty(userDto.getPassword())){
            String userName=userDto.getUserName();
            String password=userDto.getPassword();
            String verifyCode=userDto.getVerifyCode();
            HttpSession session=request.getSession();
//            String code=(String)session.getAttribute(VerifyUtil.RANDOMCODEKEY);
            String code = VerifyUtil.serCode;
            QueryWrapper<User> wrapper=new QueryWrapper<>();
            wrapper.eq("username",userName);
            User one = userService.getOne(wrapper);
            if(one.getPassword().equals(password)){
                if(code.equals(verifyCode))
                return R.ok();
                else return R.error();
            }
            else {
                return R.error();
            }
        }else {
            return R.error();
        }
    }

    @RequestMapping("/createImg")
    @ApiOperation(value="图形验证码生成")
    public void createImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片
            //将生成的随机验证码存放到redis中
            HttpSession session = request.getSession();
           VerifyUtil.serCode= (String) session.getAttribute(VerifyUtil.RANDOMCODEKEY);
             }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
