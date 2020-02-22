package com.zjnu.dormitory.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjnu.dormitory.dormitory.entity.Power;
import com.zjnu.dormitory.dormitory.entity.Role;
import com.zjnu.dormitory.dormitory.mapper.PowerMapper;
import com.zjnu.dormitory.dormitory.mapper.RoleMapper;
import com.zjnu.dormitory.dormitory.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private PowerMapper baseMapper;

    @Override
    public List<Power> findPowerByRoleName(String role) {
        QueryWrapper<Power>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("role_name",role);
        List selectList = baseMapper.selectList(queryWrapper);
        return selectList;
    }
}
