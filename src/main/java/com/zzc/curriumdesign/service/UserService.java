package com.zzc.curriumdesign.service;

import com.zzc.curriumdesign.vo.User;

import java.util.Map;

/**
 * @ClassName: UserService
 * @Author: zzc
 * @CreateTime: 2020/12/15 12:50
 * @Description: 用户业务逻辑层接口
 */

public interface UserService {
    public User getUser(String userName);
    public Map<String, Object>checkLogin(String userName,String password);
}
