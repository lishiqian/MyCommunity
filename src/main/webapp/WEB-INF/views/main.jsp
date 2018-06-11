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
    <script type="text/javascript" src="../util/bootstrap/js/bootstrap-paginator.min.js"></script>
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
    <script type="text/javascript">
        $(function () {
            var options = {
                currentPage: ${pageNum},//当前页
                totalPages: ${pages},//总页数
                numberofPages: 5,//显示的页数

                itemTexts: function(type, page, current) { //修改显示文字
                    switch (type) {
                        case "first":
                            return "第一页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "最后一页";
                        case "page":
                            return page;
                    }
                }, onPageClicked: function (event, originalEvent, type, page) { //异步换页
                    window.location.href = "/main?pageNum=" + page
                    <c:if test="${keyword != null}">
                        + "&keyword=" + ${keyword}
                    </c:if>
                    ;
                },

            };
            $("#page").bootstrapPaginator(options);

            //
            $("#search").click(function () {
                var keyword = $("#keyword").val();
                if(keyword){
                    window.location.href = "/main?keyword=" + keyword;
                }else{
                    window.location.href = "/main";
                }
            })
        });
    </script>
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
        <form class="form-group form-inline pull-right">
            <input type="text" value="${keyword}" placeholder="请输入关键字" class="form-control" id="keyword"/>
            <input type="button" value="搜索"  class="btn btn-danger" id="search"/>
        </form>
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
            <div class="row" style="text-align: center">
                <ul id="page"></ul>
            </div>
        </div>
        <div class="col-md-3" style="min-height: 500px">
            <div class="row">
                <img src="../img/advtise1.png" class="img-responsive center-block">
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
                <img src="../img/advtise2.jpg" class="img-responsive center-block">
            </div>
            <div class="row container">
                <p><a href="#">Python中文件的读取和写入</a></p>
                <p><a href="#">BP神经网络：误差反向传播公式的简单推导</a></p>
                <p><a href="#">C++实现顺序结构线性表的基本操作</a></p>
                <p><a href="#">python中列表（list）的基本操作</a></p>
                <p><a href="#">python中数组（numpy.array）的基本操作</a></p>
            </div>
        </div>
    </div>
    <div class="row">
        <div style="height:200px"></div>
    </div>
</div>
</body>
</html>