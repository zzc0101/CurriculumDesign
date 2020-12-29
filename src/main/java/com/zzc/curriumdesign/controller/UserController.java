package com.zzc.curriumdesign.controller;

import com.zzc.curriumdesign.service.DeviceService;
import com.zzc.curriumdesign.service.UserService;
import com.zzc.curriumdesign.utils.CreateVerificationImage;
import com.zzc.curriumdesign.utils.LoginInfoUtil;
import com.zzc.curriumdesign.vo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserController
 * @Author: zzc
 * @CreateTime: 2020/12/15 9:38
 * @Description: 用户登录控制器
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    /**
    * @Param: [userName, password, vcode, checkLogin, request, response, session]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: zzc
    * @DateTime: 2020/12/15 20:58
    * @Description: 用户登录控制器
    */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginCheck(String userName, String password, String vcode, boolean checkLogin, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Map<String, Object>map = new HashMap<>();
        // 验证码验证
        String saveCode = (String) session.getAttribute("verifyCode");
        if(!saveCode.equalsIgnoreCase(vcode)) {
            map.put("code",1);
            map.put("info","验证码不正确！");
        } else {
            map = userService.checkLogin(userName, password);
            if(Integer.parseInt(String.valueOf(map.get("code"))) == 0) {
                session.setAttribute("main", map.get("user"));
                // 将登录成功的IP保存
                Device device = LoginInfoUtil.initSearchLog(request);
                device.setUserName(userName);
                deviceService.insertDevice(device);
                if(checkLogin) {
                    // 设置cookie
                    Cookie cookie1 = new Cookie("userName", userName);
                    cookie1.setMaxAge(7 * 24 * 24 * 24);
                    response.addCookie(cookie1);
                    Cookie cookie2 = new Cookie("password", password);
                    cookie2.setMaxAge(7 * 24 * 24 * 24);
                    response.addCookie(cookie2);
                }
            }
        }
        return map;
    }

    /**
    * @Param: [session, response]
    * @Return: void
    * @Author: zzc
    * @DateTime: 2020/12/15 9:39
    * @Description: 创建验证码显示在页面上
    */
    @RequestMapping(value = "/createVerifyImage.do", method = RequestMethod.GET)
    public void createVerifyImage(HttpSession session,
                                  HttpServletResponse response) throws IOException {
        // 把验证码图片生成的过程封装在tools包下的CreateVerificationCodeImage类中
        CreateVerificationImage createImage = new CreateVerificationImage();
        // 产生四位随机字符串
        String vCode = createImage.createCode();
        // 将产生的四位随机字符串存放于session中（存放在session中的数据在一个会话范围内多个程序全局共享），以便验证
        session.setAttribute("verifyCode", vCode);
        // 设置返回的内容
        response.setContentType("img/jpeg");
        // 调用封装的类方法生成指定验证码字符串的内存图片
        BufferedImage image = createImage.CreateImage(vCode);
        // 获取字节流对象
        ServletOutputStream out = response.getOutputStream();
        // 将内存图像输出到浏览器，格式为JPEG
        ImageIO.write(image, "JPEG", out);
        // 刷新输出缓冲器（立即输出，而不用等待输出缓存满后才送至网络）
        out.flush();
        out.close();
    }

    /**
    * @Param: [request, response]
    * @Return: void
    * @Author: zzc
    * @DateTime: 2020/12/15 21:20
    * @Description: 注销登录清空Session
    */
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("downloadList");
        session.removeAttribute("main");
        // 用于清除 session 的所有信息
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            // 删除选定的 cookie 信息
            if ("userName".equals(cookie.getName()) || "userPassword".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

}
