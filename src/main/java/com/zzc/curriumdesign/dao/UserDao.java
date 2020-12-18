package com.zzc.curriumdesign.dao;

import com.zzc.curriumdesign.vo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: UserDao
 * @Author: zzc
 * @CreateTime: 2020/12/15 12:49
 * @Description: 用户持久层接口
 */

public interface UserDao {
    public User getUser(@Param("userName") String userName);
}
