package com.zzc.curriumdesign.service;

import com.zzc.curriumdesign.vo.Device;

import java.util.List;

/**
 * @ClassName: DeviceService
 * @Author: zzc
 * @CreateTime: 2020/12/15 19:54
 * @Description: 登录信息业务逻辑层接口
 */

public interface DeviceService {
    public int insertDevice(Device device);
    public List<Device> queryListDevice(String userName);
}
