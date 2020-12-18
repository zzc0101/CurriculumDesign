package com.zzc.curriumdesign.dao;

import com.zzc.curriumdesign.vo.Device;

import java.util.List;

/**
 * @ClassName: Device
 * @Author: zzc
 * @CreateTime: 2020/12/15 19:54
 * @Description: 登录信息持久层
 */

public interface DeviceDao {
    public List<Device> getDevice(String userName);
    public int insertDevice(Device device);
}
