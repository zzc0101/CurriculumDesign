<%--
  Created by IntelliJ IDEA.
  User: zzc
  Date: 2020/12/15
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link type="text/css" href="css/main.css" rel="stylesheet"/>
</head>
<body>
<div class="header">
    <div class="header_left">
        <img src="images/logo-bak.png"/>
    </div>
    <div class="header_right">
        <div class="user_info">
            <p>欢迎您：<span id="user_name">${main.chrName}</span>
                <a href="logout.do" id="exit_btn">【安全退出】</a>
            </p>
        </div>
        <div>
            <ul>
                <li>首页</li>
                <li><a href="translation.jsp">百度翻译</a></li>
                <li><a href="bilibili.jsp">哔哩哔哩</a></li>
                <li><a href="weather.jsp">实时天气</a></li>
                <li><a href="usercenter.jsp">个人中心</a></li>
            </ul>
        </div>
    </div>
</div>
<img src="images/t_time.jpg" id="main"/>
</body>
</html>
