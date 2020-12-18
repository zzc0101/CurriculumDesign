package com.zzc.curriumdesign.vo;

/**
 * @ClassName: User
 * @Author: zzc
 * @CreateTime: 2020/12/15 12:51
 * @Description: 用户实体类
 */

public class User {
    private String userName;
    private String password;
    private String chrName;
    private String emailAddress;
    private String imgAddress;
    private String userPhone;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChrName() {
        return chrName;
    }

    public void setChrName(String chrName) {
        this.chrName = chrName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", chrName='" + chrName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", imgAddress='" + imgAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
