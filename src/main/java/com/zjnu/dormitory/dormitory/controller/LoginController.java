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

    

}
