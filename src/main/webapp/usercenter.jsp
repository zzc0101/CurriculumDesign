<%--
  Created by IntelliJ IDEA.
  User: zzc
  Date: 2020/12/15
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh">
<head>
    <title>个人中心</title>
    <meta charset="UTF-8">
    <link type="text/css" href="css/usercenter.css" rel="stylesheet">
</head>

<body>
<div class="header">
    <div class="header_left">
        <img src="images/logo-bak.png"/>
    </div>
    <div class="header_right">
        <div class="user_info">
            <p>欢迎您：<span id="user_name">${main.chrName}</span>
                <a href="logout.do" id="exit_btn" @click="handlerLogout">【安全退出】</a>
            </p>
        </div>
        <div>
            <ul>
                <li><a href="main.jsp">首页</a></li>
                <li><a href="translation.jsp">百度翻译</a></li>
                <li><a href="bilibili.jsp">哔哩哔哩</a></li>
                <li><a href="weather.jsp">实时天气</a></li>
                <li>个人中心</li>
            </ul>
        </div>
    </div>
</div>
<div id="contains">
    <div class="contains_left">
        <div>
            <img id="headPic" class="user_img" src="<%=basePath%>${main.imgAddress}"  width="80" height="80">
            <input id="upload" name="file" accept="image/*" type="file" style="display: none"/>
            <button id="submit_btn" class="img_submit" type="submit">确定修改</button>
        </div>
        <div id="userChange">
            <div class="list_show">
                <p>账号：${main.userName}</p>
            </div>
            <div class="list_show">
                <p>昵称：${main.chrName}</p>
            </div>
            <div class="list_show" @click="handleChangeByPassword">
                <p>修改密码</p>
            </div>
            <div class="list_show">
                <p>邮箱地址：${main.emailAddress}</p>
            </div>
            <div class="list_show" @click="handleChangeByEmail">
                <p>修改邮箱</p>
            </div>
            <div class="list_show">
                <p>电话号码:${main.userPhone}</p>
            </div>
            <div class="list_show" @click="handleChangeByPhone">
                <p>修改手机</p>
            </div>
            <div class="list_show" @click="handleChangeByOut">
                <p>注销账号</p>
            </div>
        </div>
    </div>

    <div class="contains_right" id="device_history">
        <h2>登录历史</h2>
        <p class="tip_p">${main.chrName}，以下是您最近10条的登录记录，若存在异常登录记录，点击左侧的修改密码进行修改</p>
        <table class="gridtable">
            <thead>
                <tr>
                    <th>时间</th>
                    <th>IP</th>
                    <th>浏览器</th>
                    <th>登陆方式</th>
                    <th>设备</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in deviceList" :key="item.id">
                    <td>{{item.time}}</td>
                    <td>{{item.ipAddress}}</td>
                    <td>{{item.browser}}</td>
                    <td>{{item.loginMethod}}</td>
                    <td>{{item.device}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<div id="fade" class="black_overlay" onclick="CloseDiv('MyDiv','fade')">
</div>
<div id="MyDiv" class="white_content">
    <p hidden="hidden" id="hidden_p">${main.userName}</p>
    <div style="text-align: right;height: 20px;">
        <p style="text-align: center;font-size: 10px;color: #FA8072">邮箱验证码需要等待几秒</p>
        <span style="font-size: 24px;cursor:pointer;" title="点击关闭" onclick="CloseDiv('MyDiv','fade')">x</span>
    </div>
    <div class="epc" id="email_show" v-show="isEmail">
        <h2>邮箱修改</h2>
        <label>
            <p>请输入新邮箱号码：</p>
            <input class="epc_new" type="email" v-model="email_new" @blur="emailByOne" />
        </label>
        <label>
            <p>请输入新邮箱验证码：</p>
            <input class="epc_new_code" type="text" v-model="email_new_code" />
            <button class="btnCode"  @click="getEmailVerificationCode">获取验证码</button>
        </label>
        <input class="btnSubmit" @click="sendEmailUpdate" type="button" value="提交">
        <p style="color: red">{{email_error}}</p>
    </div>
    <div class="epc" id="phone_show" v-show="isPhone">
        <h2>手机号码修改</h2>
        <label>
            <p>请输入手机号码：</p>
            <input class="epc_new" @blur="checkPhone" name="phoneNew" type="text" v-model="phone_new" />
        </label>
        <label>
            <p>请输入您的邮箱验证码：</p>
            <input class="epc_new_code" type="text" v-model="phone_code" />
            <button class="btnCode" @click="getPhoneVerificationCode">获取验证码</button>
        </label>
        <input class="btnSubmit" @click="sendPhoneUpdate" type="button" value="提交">
        <p style="color: red">{{phone_error}}</p>
    </div>
    <div class="epc" id="password_show" v-show="isPassword">
        <h2>密码修改</h2>
        <label>
            <p>请输入新的密码：</p>
            <input class="epc_new" type="password" @blur="checkPassword" v-model="password_new" />
        </label>
        <label>
            <p>请再次输入新的密码：</p>
            <input class="epc_new" type="password" @blur="checkPasswordConfirm" v-model="password_confirm" />
        </label>
        <input class="btnSubmit" @click="sendPasswordUpdate" type="button" value="提交">
        <p style="color: red">{{password_error}}</p>
    </div>
</div>
<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="js/ajaxfileupload.js" type="text/javascript"></script>
<script src="js/vue.js" type="text/javascript"></script>
<script src="js/usercenter.js" type="text/javascript"></script>
</body>
</html>
