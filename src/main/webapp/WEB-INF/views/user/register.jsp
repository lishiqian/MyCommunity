<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/22
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- 引入jquery的js文件 -->
    <script type="text/javascript" src="../../util/jquery/jquery-3.3.1.min.js"></script>
    <!-- 引入bootstrap的js文件 -->
    <script type="text/javascript" src="../../util/bootstrap/js/bootstrap.js"></script>
    <!-- 引入bootstrap的css文件 -->
    <link rel="stylesheet" type="text/css" href="../../util/bootstrap/css/bootstrap.css">

    <script type="text/javascript">
        $(function () {
            $("#login").click(function () {
                var email = $("#email").val();
                var username = $("#username").val();
                var passwd = $("#passwd").val();
                var confirm_passwd = $("#confirm_passwd").val();
                
                if(!email){
                    $("#remind").text("请输入邮箱");
                    return;
                }
                if(!username){
                    $("#remind").text("请输入用户名");
                    return;
                }
                if(!passwd){
                    $("#remind").text("请输入密码");
                    return;
                }
                
                if(passwd != confirm_passwd){
                    $("#remind").text("两次密码输入不一致");
                    return;
                }
                
                $.post("/register",{email:email,username:username,passwd:passwd},function (data) {
                    if(data.code == 200){
                        alert("注册成功");
                    }else{
                        $("#remind").text(data.message);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container" style="margin-left: 50px;margin-right: 50px;">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">Register</h3>
            <div class="col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="Email" id="email" name="username" autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="Password" id="passwd" name="password" maxlength="8"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="Confirm Password" id="confirm_passwd" name="password" maxlength="8"/>
                </div>
                <div class="form-group">
                    <small id="remind"></small>
                </div>
                <div class="form-group">
                    <button class="btn btn-success btn-block" id="login">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
