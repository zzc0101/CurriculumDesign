let vm = new Vue({
    el:"#userChange",
    data:{},
    methods: {
        // 更改邮箱
        handleChangeByEmail() {
            ShowDiv('MyDiv','fade');
            vm_MyDiv.$data.isEmail = true
        },
        // 更改手机号码
        handleChangeByPhone() {
            ShowDiv('MyDiv','fade');
            vm_MyDiv.$data.isPhone = true
        },
        // 更改用户密码
        handleChangeByPassword() {
            ShowDiv('MyDiv','fade');
            vm_MyDiv.$data.isPassword = true
        },
        // 注销用户
        handleChangeByOut() {
            alert("等待注册页面，此功能还在开发中...");
        }
    }
})

let vm_MyDiv = new Vue({
    el:"#MyDiv",
    data:{
        isEmail:false,
        isPhone:false,
        isPassword:false,
        email_new:"",
        email_new_code:"",
        phone_new:"",
        phone_code:"",
        password_new:"",
        password_confirm:"",
        email_error:"",
        password_error:"",
        phone_error:"",
        isEmail_state:false,
        isPassword_state:false,
        isPhone_state:false
    },
    methods: {
        // 查询邮箱是否存在
        emailByOne() {
            // 邮箱是否只有唯一
            let champter = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if(champter.test(this.email_new)) {
                $.ajax({
                    url:"queryUserByEmail.do",
                    method:"post",
                    data:{emailAddress:vm_MyDiv.email_new},
                    dataType:"JSON",
                    success: function (response) {
                        if(!response) {
                            vm_MyDiv.email_error = "邮箱合法"
                            vm_MyDiv.isEmail_state = true;
                        } else {
                            vm_MyDiv.isEmail_state = false;
                            vm_MyDiv.email_error = "邮箱已存在"
                        }
                    }
                })
            } else {
                vm_MyDiv.email_error="请输入合法邮箱"
                vm_MyDiv.isEmail_state = false;
            }
        },
        // 获取邮箱验证码
        getEmailVerificationCode() {
            let champter = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if(this.isEmail_state) {
                if(champter.test(this.email_new)) {
                    $.ajax({
                        url:"getEmailVerificationCode.do",
                        method: "get",
                        data:{userEmail:vm_MyDiv.email_new},
                        dataType: "JSON",
                        success: function (response) {
                            if(response) {
                                vm_MyDiv.email_error="请注意查收您的邮件"
                            } else{
                                vm_MyDiv.email_error="请一分钟后再试！"
                            }
                        }
                    })
                }
            } else {
                this.email_error="请输入合法邮箱"
            }
        },
        // 发送邮箱修改申请
        sendEmailUpdate() {
            if(this.isEmail_state) {
                if(this.email_new_code!=="") {
                    $.ajax({
                        url:"updateUserInfo.do",
                        method:"post",
                        data:{
                            updateInfo:vm_MyDiv.email_new,
                            yzm:vm_MyDiv.email_new_code,
                            typeInfo:"email"
                        },
                        dataType:"JSON",
                        success:function (response) {
                            const code = response.key
                            const info = response.info
                            if(code===0) {
                                vm_MyDiv.email_error = info
                                location.reload();
                                vm_MyDiv.isEmail_state = false;
                            } else if(code === 1) {
                                vm_MyDiv.email_error = info
                            } else {
                                vm_MyDiv.email_error = info
                            }
                        }
                    })
                } else {
                    this.email_error = "请输入验证码"
                }
            }
        },
        // 修改密码
        checkPassword() {
            if(this.password_new.length>=4) {
                vm_MyDiv.password_error = ""
            } else {
                vm_MyDiv.password_error = "密码至少为4位数"
                vm_MyDiv.isPassword_state = false
            }
        },
        // 确认密码
        checkPasswordConfirm() {
            if(this.password_new === this.password_confirm) {
                vm_MyDiv.isPassword_state = true
                vm_MyDiv.password_error = ""
            } else {
                vm_MyDiv.isPassword_state = false
                vm_MyDiv.password_error = "两次输入密码不相同"
            }
        },
        // 提交密码
        sendPasswordUpdate() {
            if(this.isPassword_state) {
                $.ajax({
                    url:"updateUserInfo.do",
                    method:"post",
                    data:{
                        updateInfo:vm_MyDiv.password_confirm,
                        typeInfo:"password"
                    },
                    dataType:"JSON",
                    success:function (response) {
                        const code = response.key
                        const info = response.info
                        if(code===0) {
                            vm_MyDiv.password_error = info
                            vm_MyDiv.isPassword_state = false;
                        } else if(code === 1) {
                            vm_MyDiv.password_error = info
                        } else {
                            vm_MyDiv.password_error = info
                        }
                    }
                })
            }
        },
        // 修改手机号
        checkPhone() {
            const champter = /^((\(\d{2,3}\))|(\d{3}\-))?1(3|5|8|9)\d{9}$/;
            if(champter.test(this.phone_new)) {
                vm_MyDiv.isPhone_state = true
                vm_MyDiv.phone_error = ""
            } else {
                vm_MyDiv.isPhone_state = false
                vm_MyDiv.phone_error = "请输入合法的电话号码"
            }
        },
        // 获取验证码
        getPhoneVerificationCode() {
            if(this.isPhone_state) {
                $.ajax({
                    url:"getPhoneVerificationCode.do",
                    method:"get",
                    data:{},
                    dataType:"JSON",
                    success:function (response) {
                        if(response) {
                            vm_MyDiv.phone_error="请注意查收您的邮件"
                        } else{
                            vm_MyDiv.phone_error="请一分钟后再试！"
                        }
                    }
                })
            }
        },
        // 提交验证码
        sendPhoneUpdate() {
            if(this.isPhone_state) {
                if(this.phone_code!=="") {
                    $.ajax({
                        url:"updateUserInfo.do",
                        method:"post",
                        data:{
                            updateInfo:vm_MyDiv.phone_new,
                            yzm:vm_MyDiv.phone_code,
                            typeInfo:"phone"
                        },
                        dataType:"JSON",
                        success:function (response) {
                            const code = response.key
                            const info = response.info
                            if(code===0) {
                                vm_MyDiv.phone_error = info
                                location.reload();
                                vm_MyDiv.isPhone_state = false;
                            } else if(code === 1) {
                                vm_MyDiv.phone_error = info
                            } else {
                                vm_MyDiv.phone_error = info
                            }
                        }
                    })
                }
            } else {
                vm_MyDiv.phone_error="请检查输入项"
            }
        }
    }
})

let vm_history = new Vue({
    el:"#device_history",
    data:{
        deviceList:[]
    },
    created: function () {
        this.localLoadDevice();
    },
    methods:{
        localLoadDevice() {
            $.ajax({
                url:"queryAllDevice.do",
                data:{userName:$("#hidden_p").text()},
                dataType:"JSON",
                method:"get",
                success: function (response) {
                    vm_history.deviceList = response;
                }
            })
        },
    }
})
// 弹出隐藏层
function ShowDiv(show_div,bg_div) {
    const show = $("#"+show_div);
    const bg = $("#"+bg_div);
    show.css('display','block');
    bg.css('display','block');
    // 弹出层居中
    let windowHeight = $(window).height();  // 获取当前窗口高度
    let windowWidth = $(window).width();    // 获取当前窗口宽度
    let popupHeight = show.height(); // 获取弹出层高度
    let popupWeight = show.width();  // 获取弹出层宽度
    let posiTop = (windowHeight - popupHeight) / 2 ;
    let posiLeft = (windowWidth - popupWeight) / 2;
    show.css({"left":posiLeft+"px","top":posiTop+"px","display":"block"});
}
// 关闭弹出层
function CloseDiv(show_div, bg_div) {
    const show = $("#"+show_div);
    const bg = $("#"+bg_div);
    show.css('display','none');
    bg.css('display','none');
    vm_MyDiv.$data.isPassword = false
    vm_MyDiv.$data.isEmail = false
    vm_MyDiv.$data.isPhone = false
}
$(function() {
    //头像预览
    $("#headPic").click(function () {
        $("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
        $("#upload").on("change",function(){
            var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $("#headPic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
            }
        });
    });
    //图片上传
    $("#submit_btn").click(function () {
        var imgurl = document.getElementById("upload").value;
        if(imgurl==null || imgurl==="") return;
        $.ajaxFileUpload({
            url:"changeUserImage.do",
            fileElementId: "upload", //文件上传域的ID，这里是input的ID，而不是img的
            dataType: 'json', //返回值类型 一般设置为json
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (response) {
                alert("头像修改成功！")
                // 页面自动刷新
                location.reload();
            }
        });
    });
});
//建立一個可存取到該file的url
function getObjectURL(file) {
    var url = null ;
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}