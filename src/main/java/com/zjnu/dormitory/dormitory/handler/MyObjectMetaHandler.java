package com.zjnu.dormitory.dormitory.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyObjectMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("gmtCreatedTime",new Date(),metaObject);
        this.setFieldValByName("gmtModifyTime",new Date(),metaObject);
        this.setFieldValByName("gmtCreateTime",new Date(),metaObject);
        this.setFieldValByName("logicDelete", 0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModifyTime",new Date(),metaObject);
    }
}
