package com.zzc.curriumdesign.test;

import com.zzc.curriumdesign.service.DeviceService;
import com.zzc.curriumdesign.service.UserChangeService;
import com.zzc.curriumdesign.service.UserService;
import com.zzc.curriumdesign.vo.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.rmi.runtime.Log;

/**
 * @ClassName: TestMyBatis
 * @Author: zzc
 * @CreateTime: 2020/12/15 13:01
 * @Description: 测试MyBatis连接
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {
    private static Logger log = LogManager.getLogger(ResolverUtil.Test.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserChangeService userChangeService;
    @Test
    public void test1() {
        log.info("查询结果："+userService.getUser("admin").toString());
    }

    @Test
    public void test2() {
        Device device = new Device();
        device.setIpAddress("127.0.0.1");
        device.setLoginMethod("暂无数据");
        device.setTime("2020-12-15 19:52:24");
        device.setBrowser("Chrome");
        device.setDevice("电脑PC");
        device.setUserName("admin");
        log.info(deviceService.insertDevice(device));
    }

    @Test
    public void test3() {
        log.info(deviceService.queryListDevice("admin"));
    }

    @Test
    public void test4() {
        log.info(userChangeService.getUserEmail("318185326@qq.com"));
        log.info(userChangeService.getUserPhone("12345678999"));
    }

}
