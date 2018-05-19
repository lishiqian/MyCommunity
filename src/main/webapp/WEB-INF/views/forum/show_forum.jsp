<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <title>博客-${forum.title}</title>

    <!-- 引入jquery的js文件 -->
    <script type="text/javascript" src="../../util/jquery/jquery-3.3.1.min.js"></script>
    <!-- 引入bootstrap的js文件 -->
    <script type="text/javascript" src="../../util/bootstrap/js/bootstrap.js"></script>
    <!-- 引入bootstrap的css文件 -->
    <link rel="stylesheet" type="text/css" href="../../util/bootstrap/css/bootstrap.css">

    <style type="text/css">
        body{
            background: #f2f2f2;
        }
        #content{
            border: 1px solid #cccccc;
            background: #ffffff;
            padding: 40px;
        }

    </style>
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
                <li><a href="#">登录</a></li>
                <li><a href="#">注册</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- 主体 -->
<div class="container">
    <div class="row">
        <h2><span class="text-danger">&nbsp;&nbsp;阅读中心</span>
            <!--<a class="button btn-info btn-lg pull-right" href="/forum/add_forum_view">-->
            <!--<span class="glyphicon glyphicon-plus"> 写博客</span>-->
            <!--</a>-->
        </h2>
    </div>
    <div class="row">&nbsp;</div>
    <div class="row"><hr/></div>
    <div class="row" id="content">
        <h2>${forum.title}</h2>
        <p>2018年05月15日 18:31:03 <span class="pull-right">阅读：${forum.readingNum}</span></p>
        <hr />
        ${forum.content}
    </div>
</div>
</body>
</html>