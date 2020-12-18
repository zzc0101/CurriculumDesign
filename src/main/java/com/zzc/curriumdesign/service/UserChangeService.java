package com.zzc.curriumdesign.service;

import com.zzc.curriumdesign.vo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: UserChangeService
 * @Author: zzc
 * @CreateTime: 2020/12/16 21:20
 * @Description: 用户个人修改业务逻辑层接口
 */

public interface UserChangeService {
    public boolean getUserEmail(String emailAddress);
    public boolean getUserPhone(String userPhone);
    public boolean updateUserImage(User user, String imgAddress);
    public boolean updateUserEmail(User user, String emailAddress);
    public boolean updateUserPhone(User user, String phone);
    public boolean updateUserPassword(User user, String phone);
}
