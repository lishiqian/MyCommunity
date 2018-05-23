<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
                var username = $("#username").val();
                var passwd = $("#passwd").val();
                if(username && passwd){
                    $.post("/login",{username:username,passwd:passwd},function (data) {
                        if(data.code == 200){
                            $("#remind").text("登录成功");
                            window.parent.window.location.reload();
                        }else {
                            $("#remind").text(data.message);
                        }
                    })
                }else if(username){
                    $("#remind").text("密码不能为空");
                }else{
                    $("#remind").text("用户名不能为空");
                }
            });
        });
    </script>
</head>
<body>
<div class="container" style="margin-left: 50px;margin-right: 50px;">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">Login</h3>
            <div class="col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="Password" id="passwd" name="password" maxlength="8"/>
                </div>
                <div class="form-group">
                    <small id="remind"></small>
                </div>
                <div class="form-group">
                    <button class="btn btn-success btn-block" id="login">登录</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html> 