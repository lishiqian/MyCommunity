<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <title>我的博客</title>

    <!-- 引入jquery的js文件 -->
    <script type="text/javascript" src="../util/jquery/jquery-3.3.1.min.js"></script>
    <!-- 引入bootstrap的js文件 -->
    <script type="text/javascript" src="../util/bootstrap/js/bootstrap.js"></script>
    <!-- 引入bootstrap的css文件 -->
    <link rel="stylesheet" type="text/css" href="../util/bootstrap/css/bootstrap.css">

    <style type="text/css">
        body{
            background: #f2f2f2;
        }
        #content{
            border: 1px solid #cccccc;
            background: #ffffff;
            padding: 80px;
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
            <h2><span class="text-danger">&nbsp;&nbsp;创作中心</span>
                <a class="button btn-info btn-lg pull-right" href="/forum/add_forum_view">
                    <span class="glyphicon glyphicon-plus"> 写博客</span>
                </a>
            </h2>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row"><hr/></div>
        <div class="row" id="content">
            <div class="col-md-3">
                <ul class="nav nav-pills nav-stacked">
                    <li class="active"><a href="#">我的文章</a></li>
                    <li><a href="#">评论管理</a></li>
                    <li><a href="#">我的草稿</a></li>
                    <li><a href="#">回收站</a></li>
                </ul>
            </div>
            <div class="col-md-9" style="min-height: 500px">
                <h4>文章管理</h4>
                <hr/>
                <div class="container-fluid" >
                    <c:forEach items="${forums}" var="item" varStatus="i">
                        <div class="row" style="border-bottom: 1px solid #218838">
                            <p>&nbsp;</p>
                            <p><h4>${item.title}</h4></p>
                            <p><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.lastUpdateTime}" type="both"/> &nbsp;&nbsp;  阅读：${item.readingNum} 评论：${item.comments}
                                <span class="pull-right"><small><a target="_blank" href="/forum/show_forum?forumId=${item.id}">查看</a>|<a href="#">删除</a></small></span>
                            </p>
                        </div>
                    </c:forEach>
                    <!--<div class="row" style="border-bottom: 1px solid #218838">
                        <p>&nbsp;</p>
                        <p><h4>leetcode 125. Valid Palindrome解析</h4></p>
                        <p>2018-03-26 18:44 阅读：25 评论：14
                            <span class="pull-right"><small><a href="#">查看</a>|<a href="#">删除</a></small></span>
                        </p>
                    </div>-->

                </div>

            </div>
        </div>
    </div>
</body>
</html>