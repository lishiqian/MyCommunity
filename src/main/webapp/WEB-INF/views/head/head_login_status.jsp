<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../../util/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../../util/layer/layer.js"></script>
    <style type="text/css">
        .user-head-img {
            height: 35px;
            width: 35px;
            border: solid 2px #2dffc2;
            border-radius: 50%;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#login").click(function () {
                layer.open({
                    title:'用户登录',
                    type: 2,
                    area: ['360px', '300px'],
                    skin: 'layui-layer-rim', //加上边框
                    content: ['/show_login', 'no']
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
                    <li class="active"><a href="#">博客</a></li>
                    <li><a href="#">学院</a></li>
                    <li><a href="#">论坛</a></li>
                    <li><a href="#">发现</a></li>
                    <li><a href="#">我的博客</a></li>
                </ul>
            </div>
            <div>
                <ul class="nav navbar-nav pull-right">
                    <form class="navbar-form navbar-right visible-lg-block">
                        <div class="form-group">
                            <a href="#" ><img src="../../${login_user.headImg}" class="user-head-img"/> ${login_user.username} &nbsp;&nbsp;&nbsp;&nbsp;</a>
                            <%--<input type="submit" class="btn btn-danger btn-sm" value="退出登陆">--%>
                            <a href="/clean_user" class="btn btn-danger btn-sm">退出登陆</a>
                        </div>
                    </form>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>
