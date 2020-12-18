package com.zzc.curriumdesign.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: MD5Util
 * @Author: zzc
 * @CreateTime: 2020/12/14 17:56
 * @Description: 用于给密码加密
 */

public class MD5Util {

    /**
    * @Param: [string]
    * @Return: java.lang.String
    * @Author: zzc
    * @DateTime: 2020/12/18 14:42
    * @Description: 加密
    */
    public static String md5(String string) {
        if(string.isEmpty()) {
            return "";
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes(StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
