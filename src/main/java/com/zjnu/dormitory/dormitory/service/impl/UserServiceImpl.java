package com.zjnu.dormitory.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.form.QueryUser;
import com.zjnu.dormitory.dormitory.mapper.UserMapper;
import com.zjnu.dormitory.dormitory.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
}
