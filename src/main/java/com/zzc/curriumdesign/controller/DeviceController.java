package com.zzc.curriumdesign.controller;

import com.zzc.curriumdesign.service.DeviceService;
import com.zzc.curriumdesign.vo.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DeviceController
 * @Author: zzc
 * @CreateTime: 2020/12/16 16:08
 * @Description: 登录设备信息的控制器
 */

@Controller
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    /**
    * @Param: [userName]
    * @Return: java.util.List<com.zzc.curriumdesign.vo.Device>
    * @Author: zzc
    * @DateTime: 2020/12/18 14:41
    * @Description: 通过用户名获取用户登录的设备信息
    */
    @RequestMapping(value = "queryAllDevice.do",method = RequestMethod.GET)
    @ResponseBody
    private List<Device> queryAllDevice(@Param("userName") String userName) {
        return deviceService.queryListDevice(userName);
    }
}
