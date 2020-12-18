package com.zzc.curriumdesign.vo;

/**
 * @ClassName: Device
 * @Author: zzc
 * @CreateTime: 2020/12/15 18:04
 * @Description: 设备登录信息类
 */

public class Device {
    private int id;
    private String IpAddress;
    private int b_id;
    private String LoginMethod;
    private int d_id;
    private String Time;
    private String Browser;
    private String Device;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Device() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getLoginMethod() {
        return LoginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        LoginMethod = loginMethod;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String browser) {
        Browser = browser;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", IpAddress='" + IpAddress + '\'' +
                ", b_id=" + b_id +
                ", LoginMethod='" + LoginMethod + '\'' +
                ", d_id=" + d_id +
                ", Time='" + Time + '\'' +
                ", Browser='" + Browser + '\'' +
                ", Device='" + Device + '\'' +
                '}';
    }
}
