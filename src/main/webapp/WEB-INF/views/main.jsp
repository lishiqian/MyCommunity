<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <title>博客首页</title>

    <!-- 引入jquery的js文件 -->
    <script type="text/javascript" src="../util/jquery/jquery-3.3.1.min.js"></script>
    <!-- 引入bootstrap的js文件 -->
    <script type="text/javascript" src="../util/bootstrap/js/bootstrap.js"></script>
    <!-- 引入bootstrap的css文件 -->
    <link rel="stylesheet" type="text/css" href="../util/bootstrap/css/bootstrap.css">

    <style type="text/css">
        body {
            background: #f2f2f2;
        }

        #content {
            border: 1px solid #cccccc;
            background: #ffffff;
            /*padding: 80px;*/
        }
        .forum_item{
            border: 1px solid #cccccc;
        }
        .user-head {
            height: 30px;
            width: 30px;
            border: solid 2px #2dffc2;
            border-radius: 50%;
        }

    </style>
</head>
<body>
<jsp:include page="/head" flush="true"></jsp:include>


<!-- 主体 -->
<div class="container">
    <div class="row">
        <h2><span class="text-danger">&nbsp;&nbsp;技术论坛</span>
            <a class="button btn-info btn-lg pull-right" href="/forum/add_forum_view">
                <span class="glyphicon glyphicon-plus"> 写博客</span>
            </a>
        </h2>
    </div>
    <div class="row">&nbsp;</div>
    <div class="row">
        <hr/>
    </div>
    <div class="row" id="content">
        <div class="col-md-9" style="min-height: 500px">
            <c:forEach items="${forumUserCustoms}" var="item" varStatus="i">
                <div class="row container-fluid forum_item">
                    <p>&nbsp;</p>
                    <p><h4>${item.forum.title}</h4></p>
                    <p>
                    <span>
                        <img src="../${item.user.headImg}" class="user-head"/>
                        &nbsp;
                        <a style="font-size: 15px">${item.user.username}</a>
                        &nbsp;&nbsp;&nbsp;
                    </span>
                        <span>
                            <fmt:formatDate pattern="MM-dd" value="${item.forum.lastUpdateTime}" type="both"/> &nbsp;&nbsp;
                            阅读：${item.forum.readingNum} &nbsp;&nbsp;
                            评论：${item.forum.comments}</span>
                        <span class="pull-right"><small><a target="_blank" href="/forum/show_forum?forumId=${item.forum.id}">查看</a></small></span>
                    </p>
                </div>
            </c:forEach>
            <%--<div class="row container-fluid forum_item">--%>
                <%--<p>&nbsp;</p>--%>
                <%--<p><h4>leetcode 125. Valid Palindrome解析</h4></p>--%>
                <%--<p>--%>
                    <%--<span>--%>
                        <%--<img src="../img/head2.jpg" class="user-head"/>--%>
                        <%--&nbsp;--%>
                        <%--<a style="font-size: 15px">不好玩</a>--%>
                        <%--&nbsp;&nbsp;&nbsp;--%>
                    <%--</span>--%>
                    <%--<span>03-26 &nbsp;&nbsp; 阅读：25 &nbsp;&nbsp; 评论：14</span>--%>
                    <%--<span class="pull-right"><small><a href="#">查看</a>|<a href="#">删除</a></small></span>--%>
                <%--</p>--%>
            <%--</div>--%>
        </div>
        <div class="col-md-3" style="min-height: 500px">
            <div class="row">
                <img src="../img/广告.png"  class="img-responsive center-block">
            </div>
            <div class="row container">
                <p><a href="#">程序员不要去这样的公司</a></p>
                <p><a href="#">你的知识死角不能否定你的技术能力</a></p>
                <p><a href="#">【原创】分布式之redis复习精讲</a></p>
                <p><a href="#">Docker 镜像之进阶篇</a></p>
                <p><a href="#">vue2.0 axios封装、vuex介绍</a></p>
                <p><a href="#">学ASP.NET入门编程，合适么?</a></p>
                <p><a href="#">《HelloGitHub》第 26 期</a></p>
                <p><a href="#">上周热点回顾（5.21-5.27）</a></p>
                <p><a href="#">对Javascript 类、原型链、继承的理解</a></p>
                <p><a href="#">一步一步理解 python web 框架</a></p>
            </div>
            <div class="row">
                <img src="../img/广告.png"  class="img-responsive center-block">
            </div>
            <div class="row container">
                <p><a href="#">程序员不要去这样的公司</a></p>
                <p><a href="#">你的知识死角不能否定你的技术能力</a></p>
                <p><a href="#">【原创】分布式之redis复习精讲</a></p>
                <p><a href="#">Docker 镜像之进阶篇</a></p>
                <p><a href="#">vue2.0 axios封装、vuex介绍</a></p>
                <p><a href="#">学ASP.NET入门编程，合适么?</a></p>
                <p><a href="#">《HelloGitHub》第 26 期</a></p>
                <p><a href="#">上周热点回顾（5.21-5.27）</a></p>
                <p><a href="#">对Javascript 类、原型链、继承的理解</a></p>
                <p><a href="#">一步一步理解 python web 框架</a></p>
            </div>
        </div>
    </div>
    <div class="row">
        <div style="height:200px"></div>
    </div>
</div>
</body>
</html>