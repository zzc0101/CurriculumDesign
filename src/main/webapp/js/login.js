// 提交数据并检测不为空
let vm2 = new Vue({
    el: "#user_login",
    data:{
        userName:"",
        password:"",
        userCode:"",
        checkLogin:false,
        nameError:false,
        pwdError:false,
        codeError:false,
        img_src:"createVerifyImage.do"
    },
    methods: {
        changImage() {
            this.img_src='createVerifyImage.do?i='+Math.random();
        },
        handlerBlurByName() {
            this.nameError = this.userName === ""
        },
        handlerBlurByPwd() {
            this.pwdError = this.password === ""
        },
        handlerBlurByCode() {
            this.codeError = this.userCode === ""
        },
        handlerLogin() {
            this.nameError = this.userName === ""
            this.pwdError = this.password === ""
            this.codeError = this.userCode === ""
            if(!this.nameError && !this.pwdError && !this.codeError) {
                $.ajax({
                    type:"post",
                    url:"login.do",
                    data:{
                        userName:this.userName,
                        password:this.password,
                        vcode:this.userCode,
                        checkLogin: this.checkLogin
                    },
                    dataType:"json",
                    success: function (response) {
                        const code = response.code
                        const info = response.info
                        if(code === 0) {
                            window.location.href='main.jsp';
                            $("#info_show").text("");
                        } else if(code === 1) {
                            $("#info_show").text(info);
                        } else if(code === 2) {
                            $("#info_show").text(info);
                            vm2.changImage();
                        }
                    }
                })
            } else {
                alert("请检查输入项")
            }
        }
    }
})
