package com.zzc.curriumdesign.utils;

import com.zzc.curriumdesign.vo.Device;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: LoginInfoUtil
 * @Author: zzc
 * @CreateTime: 2020/12/15 16:49
 * @Description: 用户登录信息的解析
 */

public class LoginInfoUtil {
    /**
    * @Param: [request]
    * @Return: java.lang.String
    * @Author: zzc
    * @DateTime: 2020/12/18 14:43
    * @Description: 通过请求获取登录设备 IP（实际中，请求会经过多次跳转）
    */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
    * @Param: [request]
    * @Return: com.zzc.curriumdesign.vo.Device
    * @Author: zzc
    * @DateTime: 2020/12/18 14:42
    * @Description: 通过登录请求获取设备信息
    */
    public static Device initSearchLog(HttpServletRequest request) {
        // userAgent中有很多获取请求信息的方法
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        Device device = new Device();
        device.setLoginMethod("暂无数据");
        device.setIpAddress(getIpAddress(request));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        device.setTime(simpleDateFormat.format(date));
        if(browser.getBrowserType().toString().contains("WEB")) {
            device.setDevice("电脑PC");
            device.setBrowser(browser.getName().toString().split(" ")[0]);
        } else if(browser.getBrowserType().toString().contains("MOBILE")) {
            if(browser.getManufacturer().toString().contains("APPLE")) {
                device.setDevice("IOS");
                device.setBrowser(browser.getName().toString().split(" ")[1]);
            } else if(browser.getManufacturer().toString().contains("GOOGLE")) {
                device.setDevice("Android");
                device.setBrowser(browser.getName().toString().split(" ")[0]);
            } else {
                device.setDevice("其他");
            }
        } else {
            device.setBrowser("其他");
            device.setBrowser(browser.getName().toString().split(" ")[0]);
        }
        return device;
    }
}
