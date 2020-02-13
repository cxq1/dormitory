package com.zjnu.dormitory.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnu.dormitory.dormitory.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjnu.dormitory.dormitory.form.QueryUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
public interface UserService extends IService<User> {

    void pageList(Page<User> userPage, QueryUser queryUser);

    User selectUser(String uid, String oldPw,String name);
}
