package com.zzc.curriumdesign.service.impl;

import com.zzc.curriumdesign.dao.UserDao;
import com.zzc.curriumdesign.service.UserService;
import com.zzc.curriumdesign.utils.MD5Util;
import com.zzc.curriumdesign.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserServiceImpl
 * @Author: zzc
 * @CreateTime: 2020/12/15 12:51
 * @Description: 用户业务逻辑实现类
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    /**
    * @Param: [userName]
    * @Return: com.zzc.curriumdesign.vo.User
    * @Author: zzc
    * @DateTime: 2020/12/15 20:06
    * @Description: 通过用户名获取用户信息
    */
    @Override
    public User getUser(String userName) {
        return userDao.getUser(userName);
    }
    
    /**
    * @Param: [userName, password]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: zzc
    * @DateTime: 2020/12/15 20:06
    * @Description: 登录检测
    */
    @Override
    public Map<String, Object> checkLogin(String userName, String password) {
        Map<String, Object> map = new HashMap<>();
        User userResult =userDao.getUser(userName);
        if(MD5Util.md5(password).equals(userResult.getPassword())) {
            map.put("code",0);
            map.put("info","登录成功");
            map.put("user",userResult);
        } else {
            map.put("code",2);
            map.put("info","检查用户名和密码是否错误");
        }
        return map;
    }
}
