package com.zjnu.dormitory.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.zjnu.dormitory.dormitory.dto.CacheUser;
import com.zjnu.dormitory.dormitory.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjnu.dormitory.dormitory.form.QueryUser;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Set;

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


    Set<String> findRolesByUsername(String username);

    Set<String> findPermissionsByUsername(String username);

    User findByUserName(String username);

    CacheUser login(String userName, String password);


}
