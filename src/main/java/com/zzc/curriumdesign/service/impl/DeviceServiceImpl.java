package com.zzc.curriumdesign.service.impl;

import com.zzc.curriumdesign.dao.DeviceDao;
import com.zzc.curriumdesign.service.DeviceService;
import com.zzc.curriumdesign.vo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DeviceServiceImpl
 * @Author: zzc
 * @CreateTime: 2020/12/15 19:56
 * @Description:
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    /**
    * @Param: [device]
    * @Return: void
    * @Author: zzc
    * @DateTime: 2020/12/15 20:05
    * @Description: 保存设备信息
    */
    @Override
    public int insertDevice(Device device) {
        int index = deviceDao.insertDevice(device);
        return index;
    }

    /**
    * @Param: [userName]
    * @Return: java.util.List<com.zzc.curriumdesign.vo.Device>
    * @Author: zzc
    * @DateTime: 2020/12/16 16:10
    * @Description: 通过最近登录的记录查询近 10 次的记录
    */
    @Override
    public List<Device> queryListDevice(String userName) {
        return deviceDao.getDevice(userName);
    }
}
