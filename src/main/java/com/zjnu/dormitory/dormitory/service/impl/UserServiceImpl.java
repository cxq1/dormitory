package com.zjnu.dormitory.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.dto.UserDto;
import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.entity.Role;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.form.QueryUser;
import com.zjnu.dormitory.dormitory.mapper.UserMapper;
import com.zjnu.dormitory.dormitory.service.RoleService;
import com.zjnu.dormitory.dormitory.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PowerServiceImpl powerService;

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
        return queryWrapper.getEntity();
    }


}
