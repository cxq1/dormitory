package com.zjnu.dormitory.dormitory.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zjnu.dormitory.dormitory.common.R;
import com.zjnu.dormitory.dormitory.dto.UserDto;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.service.UserService;
import com.zjnu.dormitory.dormitory.utils.VerifyCodeUtils;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

//import static com.zjnu.dormitory.dormitory.utils.VerifyUtil.RANDOMCODEKEY;

@RestController
@CrossOrigin
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
                    if("admin".equals(one.getRoleName())) {
                        request.getSession().setAttribute("admin", one);
                        return R.ok();
                    }
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
    public R userLogin(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody UserDto userDto, HttpSession session){
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
                    request.getSession().setAttribute("user",one);
                    User user = (User) request.getSession().getAttribute("user");
//                    System.out.println(session.getId());
//                    System.out.println("***********************************************");
                    return R.ok();
                }
                else return R.error().message("验证码错误!");
            }
            else {
                return R.error().message("账号或密码错误！1");
            }
        }else {
            return R.error().message("账号或密码错误！2");

        }
    }



}
