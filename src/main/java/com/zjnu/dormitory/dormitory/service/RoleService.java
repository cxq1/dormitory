package com.zjnu.dormitory.dormitory.service;

import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
public interface RoleService extends IService<Role> {

    List<Power> findPowerByRoleName(String role);
}
