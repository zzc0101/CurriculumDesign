package com.zzc.curriumdesign.service.impl;

import com.zzc.curriumdesign.dao.UserChangeDao;
import com.zzc.curriumdesign.service.UserChangeService;
import com.zzc.curriumdesign.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserChangeServiceImpl
 * @Author: zzc
 * @CreateTime: 2020/12/16 21:23
 * @Description: 用户个人修改业务逻辑层实现类
 */

@Service
public class UserChangeServiceImpl implements UserChangeService {
    @Autowired
    private UserChangeDao userChangeDao;

    /**
    * @Param: [emailAddress]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/16 21:29
    * @Description: 通过邮箱获取用户用于验证用户是否存在
    */
    @Override
    public boolean getUserEmail(String emailAddress) {
        User user = userChangeDao.getUserEmail(emailAddress);
        return user != null;
    }

    /**
    * @Param: [userPhone]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/16 21:29
    * @Description: 通过电话号码查询用户是否存在
    */
    @Override
    public boolean getUserPhone(String userPhone) {
        User user = userChangeDao.getUserPhone(userPhone);
        return user != null;
    }

    /**
    * @Param: [user, imgAddress]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/18 13:25
    * @Description: 通过用户名在数据库中修改头像的路径
    */
    @Override
    public boolean updateUserImage(User user, String imgAddress) {
        int updateImage = userChangeDao.updateImage(user.getUserName(), imgAddress);
        return updateImage > 0;
    }

    /**
    * @Param: [user, emailAddress]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/18 14:32
    * @Description: 通过用户名在数据库中修改用户邮箱
    */
    @Override
    public boolean updateUserEmail(User user, String emailAddress) {
        int updateEmail = userChangeDao.updateEmailAddress(user.getUserName(), emailAddress);
        return updateEmail > 0;
    }

    /**
     * @Param: [user, emailAddress]
     * @Return: boolean
     * @Author: zzc
     * @DateTime: 2020/12/18 14:32
     * @Description: 通过用户名在数据库中修改用户手机号码
     */
    @Override
    public boolean updateUserPhone(User user, String userPhone) {
        int updatePhone = userChangeDao.updatePhone(user.getUserName(), userPhone);
        return updatePhone > 0;
    }

    /**
    * @Param: [user, password]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/18 14:32
    * @Description: 通过用户名在数据库中修改用户密码
    */
    @Override
    public boolean updateUserPassword(User user, String password) {
        int updatePassword = userChangeDao.updatePassword(user.getUserName(), password);
        return updatePassword > 0;
    }
}
