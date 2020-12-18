package com.zzc.curriumdesign.utils;

import java.util.Random;

/**
 * @ClassName: EmailUtils
 * @Author: zzc
 * @CreateTime: 2020/12/17 10:52
 * @Description: 随机数生成六位验证码
 */

public class EmailUtils {
    /**
    * @Param: []
    * @Return: java.lang.String
    * @Author: zzc
    * @DateTime: 2020/12/18 14:44
    * @Description: 生成随机的六位验证码
    */
    public static String getVerification() {
        StringBuilder random = new StringBuilder();
        char zf[] = {'0','1','2','3','4','5','6','7','8','9','a','A','b','B','c','C','d','D','f','F','g','G',
        'h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T',
                'u','U','v','V','w','W','x','X','y','Y','z','Z'};
        for(int i = 0;i<6;i++) {
            Random rd = new Random();
            int k = rd.nextInt(60);
            random.append(zf[k]);
        }
        return random.toString();
    }
}
