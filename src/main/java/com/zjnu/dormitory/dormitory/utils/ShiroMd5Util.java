package com.zjnu.dormitory.dormitory.utils;

import com.zjnu.dormitory.dormitory.entity.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroMd5Util {
    public static String  SysMd5(User xxx) {
        String hashAlgorithmName = "MD5";//加密方式

        Object crdentials =xxx.getPassword();//密码原值

        ByteSource salt = ByteSource.Util.bytes(xxx.getUsername());//以账号作为盐值

        int hashIterations = 1024;//加密1024次

        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);

        return hash.toString();
    }
}
