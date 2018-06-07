<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../../util/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../../util/layer/layer.js"></script>
    <script type="text/javascript" src="../../js/common.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#login").click(function () {
                layer.open({
                    title:'用户登录',
                    type: 2,
                    area: ['360px', '330px'],
                    skin: 'layui-layer-rim', //加上边框
                    content: ['/show_login', 'no']
                });
            });

            $("#register").click(function () {
                layer.open({
                    title:'用户注册',
                    type: 2,
                    area: ['360px', '430px'],
                    skin: 'layui-layer-rim', //加上边框
                    content: ['/show_register', 'no']
                });
            });
        });
    </script>
</head>
<body>
    <!--导航栏-->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">技术论坛</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/main">博客</a></li>
                    <li><a href="#">学院</a></li>
                    <li><a href="#">论坛</a></li>
                    <li><a href="#">发现</a></li>
                    <li><a href="/user/show_user">我的博客</a></li>
                </ul>
            </div>
            <div>
                <ul class="nav navbar-nav pull-right">
                    <li><a href="#" id="login">登录</a></li>
                    <li><a href="#" id="register">注册</a></li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>
