package com.zjnu.dormitory.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.dto.CacheUser;
import com.zjnu.dormitory.dormitory.dto.UserDto;
import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.entity.Role;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.form.QueryUser;
import com.zjnu.dormitory.dormitory.handler.DorException;
import com.zjnu.dormitory.dormitory.mapper.UserMapper;
import com.zjnu.dormitory.dormitory.service.RoleService;
import com.zjnu.dormitory.dormitory.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PowerServiceImpl powerService;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void pageList(Page<User> userPage, QueryUser queryUser) {
        if(queryUser==null){
            baseMapper.selectPage(userPage,null);
            return;
        }
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        String username = queryUser.getUsername();
        String mail = queryUser.getMail();
        String name = queryUser.getName();
        String roleName = queryUser.getRoleName();
        String number = queryUser.getNumber();

        if(!StringUtils.isEmpty(username)){
            queryWrapper.eq("username",username);
        }
        if(!StringUtils.isEmpty(mail)){
            queryWrapper.eq("mail",mail);
        }
        if(!StringUtils.isEmpty(name)){
            queryWrapper.eq("name",name);
        }
        if(!StringUtils.isEmpty(roleName)){
            queryWrapper.eq("role_name",roleName);
        }
        if(!StringUtils.isEmpty(number)){
            queryWrapper.eq("number",number);
        }

        baseMapper.selectPage(userPage,queryWrapper);
    }

    @Override
    public User selectUser(String uid, String oldPw,String name) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("password",oldPw);
        queryWrapper.eq("name",name);
        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public Set<String> findRolesByUsername(String username) {
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = baseMapper.selectOne(queryWrapper);
        String roleName = user.getRoleName();
        HashSet<String>set=new HashSet<>();
        set.add(roleName);
        return set;
    }

    @Override
    public Set<String> findPermissionsByUsername(String username) {
        Set<String> roles = this.findRolesByUsername(username);
        QueryWrapper<Power>queryWrapper=new QueryWrapper<>();
        for (String role : roles) {
            queryWrapper.eq("role_name",role);
        }
        Set<String> set = powerService.list(queryWrapper).stream().map(e->e.getPowerRule()).collect(Collectors.toSet());
        return set;
    }

    @Override
    public User findByUserName(String username) {
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    public CacheUser login(String userName, String password){
        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        CacheUser cacheUser;

        // 4、认证
        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建缓存用户信息返回给前端
            User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder()
                    .token(currentUser.getSession().getId().toString())
                    .build();
            BeanUtils.copyProperties(user, cacheUser);
            log.warn("CacheUser is {}", cacheUser.toString());
        } catch (UnknownAccountException e) {
            log.error("账户不存在异常：", e);
            throw new DorException(4001,"账号不存在!");
        } catch (IncorrectCredentialsException e) {
            log.error("凭据错误（密码错误）异常：", e);
            throw new DorException(4001,"密码不正确!");
        } catch (AuthenticationException e) {
            log.error("身份验证异常:", e);
            throw new DorException(4001, "用户验证失败!");
        }
        return cacheUser;
    }


    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }



}
