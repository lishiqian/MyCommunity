<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <title>我的博客</title>

    <!-- 引入jquery的js文件 -->
    <script type="text/javascript" src="../../util/jquery/jquery-3.3.1.min.js"></script>
    <!-- 引入bootstrap的js文件 -->
    <script type="text/javascript" src="../../util/bootstrap/js/bootstrap.js"></script>
    <!-- 引入bootstrap的css文件 -->
    <link rel="stylesheet" type="text/css" href="../../util/bootstrap/css/bootstrap.css">

    <style type="text/css">
        body {
            background: #f2f2f2;
        }

        #content {
            border: 1px solid #cccccc;
            background: #ffffff;
            padding: 80px;
        }
    </style>

    <script type="text/javascript">
        var deleteForun = function(forumId,userId){
            //信息框-例2
            layer.msg('你确定要删除么？', {
                time: 0 //不自动关闭
                ,btn: ['确定', '取消']
                ,yes: function(index){
                    layer.close(index);
                    window.location.href = "/forum/forum_delete?userId="+userId+"&forumId=" + forumId;
                }
            });
        }
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
                    window.location.href = "/forum/forum_list?status=1&pageNum=" + page;
                },

            };
            $("#page").bootstrapPaginator(options);
        });
    </script>
</head>
<body>
<jsp:include page="/head" flush="true"></jsp:include>


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
    <div class="row">
        <hr/>
    </div>
    <div class="row" id="content">
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="/user/show_user">我的信息</a></li>
                <li class="active"><a href="/forum/forum_list?status=1">我的帖子</a></li>
                <li><a href="/forum/forum_list?status=2">我的草稿</a></li>
                <li><a href="/forum/forum_list?status=3">回收站</a></li>
                <li><a href="/forum/forum_comment_manager_list">评论管理</a></li>
            </ul>
        </div>
        <div class="col-md-9" style="min-height: 500px">
            <h4>文章管理</h4>
            <hr/>
            <div class="container-fluid">
                <c:if test="${empty forums}">
                    <div class="jumbotron">
                        <div class="container">
                            <h3>帖子空空如也！！！</h3>
                        </div>
                    </div>
                </c:if>
                <c:forEach items="${forums}" var="item" varStatus="i">
                    <div class="row" style="border-bottom: 1px solid #218838">
                        <p>&nbsp;</p>
                        <p><h4>${item.title}</h4></p>
                        <p><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.lastUpdateTime}" type="both"/>
                            &nbsp;&nbsp; 阅读：${item.readingNum} 评论：${item.comments}
                            <span class="pull-right">
                                <small>
                                    <a target="_blank" href="/forum/show_forum?forumId=${item.id}">查看</a>|
                                    <a href="javascript:void(0)" onclick="deleteForun(${item.id},${item.userId})">删除</a>
                                </small>
                            </span>
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
                <div class="row" style="text-align: center">
                    <ul id="page"></ul>
                </div>
            </div>

        </div>
    </div>
    <div class="row">
        <div style="height:200px"></div>
    </div>
</div>
</body>
</html>