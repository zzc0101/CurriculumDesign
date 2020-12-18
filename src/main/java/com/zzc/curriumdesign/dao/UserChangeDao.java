package com.zzc.curriumdesign.dao;

import com.zzc.curriumdesign.vo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: UserChangeDao
 * @Author: zzc
 * @CreateTime: 2020/12/16 21:21
 * @Description: 用户个人修改的持久层接口
 */

public interface UserChangeDao {
    public User getUserEmail(@Param("emailAddress") String emailAddress);
    public User getUserPhone(@Param("userPhone") String userPhone);
    public int updateImage(@Param("userName")String userName, @Param("imgAddress")String imgAddress);
    public int updateEmailAddress(@Param("userName")String userName, @Param("emailAddress")String emailAddress);
    public int updatePassword(@Param("userName")String userName, @Param("password")String password);
    public int updatePhone(@Param("userName")String userName, @Param("userPhone")String userPhone);
}
