package com.zzc.curriumdesign.controller;

import com.zzc.curriumdesign.service.UserChangeService;
import com.zzc.curriumdesign.service.UserService;
import com.zzc.curriumdesign.utils.EmailUtils;
import com.zzc.curriumdesign.utils.MD5Util;
import com.zzc.curriumdesign.utils.SendEmail;
import com.zzc.curriumdesign.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName: UserChangeController
 * @Author: zzc
 * @CreateTime: 2020/12/16 10:52
 * @Description: 用户修改信息控制器
 */

@Controller
public class UserChangeController {
    @Autowired
    private UserChangeService userChangeService;
    @Autowired
    private UserService userService;
    boolean emailFlag = true;
    boolean phoneFlag = true;
    static String randomEmailYzm = "";
    static String randomPhoneYzm = "";

    /**
    * @Param: [file, request]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/17 23:14
    * @Description: 上传头像，修改用户头像保存路径，刷新session保存值
    */
    @RequestMapping(value = "/changeUserImage.do",method = RequestMethod.POST)
    @ResponseBody
    private boolean changeUserImage(@RequestParam() MultipartFile file, HttpServletRequest request) throws IOException {
        // 先获取到要上传的文件目录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("main");
        String path = request.getSession().getServletContext().getRealPath("/images/userico");
        // 创建File对象，一会向该路径下上传文件
        File filePath = new File(path);
        // 判断路径是否存在，如果不存在，创建该路径
        if(!filePath.exists()) {
            filePath.mkdirs();
        }
        if (null != file) {
            //原始名称
            String originalFilename = file.getOriginalFilename();
            //新图片名称
            String newFilename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
            //新图片存储路径
            File fileNew = new File(path+"/"+newFilename);
            //将内存中的数据保存到磁盘
            file.transferTo(fileNew);
            userChangeService.updateUserImage(user,"/images/userico/"+newFilename);
            // 重新保存session
            session.setAttribute("main",userService.getUser(user.getUserName()));
            return true;
        }
        return false;
    }

    /**
    * @Param: [email]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/16 21:17
    * @Description: 通过邮箱查询改邮箱是否存在
    */
    @RequestMapping(value = "/queryUserByEmail.do", method = RequestMethod.POST)
    @ResponseBody
    private boolean getUserEmail(String emailAddress) {
        return userChangeService.getUserEmail(emailAddress);
    }

    /**
    * @Param: [phone]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/16 22:03
    * @Description: 通过电话号码查询电话是否存在
    */
    @RequestMapping(value = "/queryUserByPhone.do", method = RequestMethod.POST)
    @ResponseBody
    private boolean getUserPhone(String userPhone) {
        return userChangeService.getUserPhone(userPhone);
    }

    /**
    * @Param: [useremail]
    * @Return: java.lang.String
    * @Author: zzc
    * @DateTime: 2020/12/17 10:07
    * @Description: 修改邮箱时获取邮箱验证码
    */
    @RequestMapping(value = "/getEmailVerificationCode.do", method =  RequestMethod.GET)
    @ResponseBody
    private boolean getEmailVerificationCode(String userEmail) {
        if(!emailFlag) {
            return false;
        }
        System.out.println(userEmail);
        randomEmailYzm = EmailUtils.getVerification();
        try {
            SendEmail sendEmail = new SendEmail();
            sendEmail.setReceiveMailAccount(userEmail);
            sendEmail.setInfo(randomEmailYzm);
            sendEmail.Send();
            emailFlag = false;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    emailFlag = true;
                }
            },1000*60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
    * @Param: [userEmail]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/18 14:39
    * @Description: 修改手机号码时邮箱验证码，为以后API调用更改使用
    */
    @RequestMapping(value = "/getPhoneVerificationCode.do", method =  RequestMethod.GET)
    @ResponseBody
    private boolean getPhoneVerificationCode(HttpServletRequest request) {
        if(!phoneFlag) {
            return false;
        }
        User user = (User) request.getSession().getAttribute("main");
        randomPhoneYzm = EmailUtils.getVerification();
        try {
            SendEmail sendEmail = new SendEmail();
            sendEmail.setReceiveMailAccount(user.getEmailAddress());
            sendEmail.setInfo(randomPhoneYzm);
            sendEmail.Send();
            phoneFlag = false;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    phoneFlag = true;
                }
            },1000*60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
    * @Param: [userName, updateInfo, update_id]
    * @Return: boolean
    * @Author: zzc
    * @DateTime: 2020/12/17 10:10
    * @Description: updateInfo为修改的信息，update_id为标识位，userName为判断条件
    */
    @RequestMapping(value = "/updateUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> updateUserInfo(String updateInfo, String typeInfo, String yzm,HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("main");
        if("email".equalsIgnoreCase(typeInfo)) {
            if(!randomEmailYzm.equalsIgnoreCase(yzm)) {
                map.put("key",2);
                map.put("info","邮箱验证码错误！");
                return map;
            }
            boolean flag = userChangeService.updateUserEmail(user, updateInfo);
            if(flag) {
                map.put("key",0);
                map.put("info","邮箱修改成功");
                user.setEmailAddress(updateInfo);
                session.setAttribute("main",user);
            } else {
                map.put("key",1);
                map.put("info","邮箱修改失败");
            }
        } else if("phone".equalsIgnoreCase(typeInfo)) {
            if(!randomPhoneYzm.equalsIgnoreCase(yzm)) {
                map.put("key",2);
                map.put("info","邮箱验证码错误！");
                return map;
            }
            boolean flag = userChangeService.updateUserPhone(user, updateInfo);
            if(flag) {
                map.put("key",0);
                map.put("info","手机号码修改成功");
                user.setUserPhone(updateInfo);
                session.setAttribute("main",user);
            } else {
                map.put("key",1);
                map.put("info","手机号码修改失败");
            }
        } else if("password".equalsIgnoreCase(typeInfo)){
            boolean flag = userChangeService.updateUserPassword(user, MD5Util.md5(updateInfo));
            if(flag) {
                map.put("key",0);
                map.put("info","密码修改成功");
                user.setPassword(updateInfo);
                session.setAttribute("main",user);
            } else {
                map.put("key",1);
                map.put("info","密码修改失败");
            }
        }
        return map;
    }
}
