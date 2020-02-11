package com.zjnu.dormitory.dormitory.service.impl;

import com.zjnu.dormitory.dormitory.entity.User;
import com.zjnu.dormitory.dormitory.mapper.UserMapper;
import com.zjnu.dormitory.dormitory.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
