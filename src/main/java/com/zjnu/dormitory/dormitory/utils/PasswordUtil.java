package com.zjnu.dormitory.dormitory.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;

public class PasswordUtil {
    public static String encodePwd(String pw){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] b = pw.getBytes();
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
                    '6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
            StringBuffer sb = new StringBuffer();
            for (byte bb : digest) {
                sb.append(chars[(bb>>4)&15]);
                sb.append(chars[bb&15]);
            }
            System.out.println(sb);
            return String.valueOf(sb);
        }catch (Exception e){
            throw new RuntimeException("ff");

        }
    }
    public String Base64Encode(String res) {
        return Base64.encode(res.getBytes());
    }

    /**
     * 使用Base64进行解密
     * @param res
     * @return
     */
    public String Base64Decode(String res) {
        return new String(Base64.decode(res));
    }
}
