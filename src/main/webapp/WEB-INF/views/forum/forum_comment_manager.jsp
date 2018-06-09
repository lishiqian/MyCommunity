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
        .forum_content {
            border: 1px solid #cccccc;
            background: #ffffff;
            padding-left: 40px;
            padding-right: 40px;
            padding-top: 40px;
        }

        .user-head {
            height: 50px;
            width: 50px;
            border: solid 2px #2dffc2;
            border-radius: 50%;
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
           $(".forum_manager_btn").click(function () {
               var status = $(this).attr("status");
               var that = $(this);
               //切换状态
               if(status == 1){
                   status = 2;
               }else{
                   status = 1;
               }
               var commentId = $(this).attr("commentId");

               $.get("/forum_comment/forum_comment_manager",{
                   commentId:commentId,
                   status:status
               },function (data) {
                   if(data.code == 200){
                       if(data.data == 1){
                           that.html("<span class='btn bg-danger btn-sm pull-right' >删除评论</span>");
                       }else{
                           that.html("<span class='btn bg-success btn-sm pull-right' >恢复删除</span>");
                       }
                       that.attr("status",data.data);
                   }
               })
           });
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
                <li><a href="/forum/forum_list?status=1">我的帖子</a></li>
                <li><a href="/forum/forum_list?status=2">我的草稿</a></li>
                <li><a href="/forum/forum_list?status=3">回收站</a></li>
                <li class="active"><a href="/forum/forum_comment_manager_list">评论管理</a></li>
            </ul>
        </div>
        <div class="col-md-9" style="min-height: 500px">
            <div class="row">
                <div class="row forum_content">
                    <h2>${forum.title}</h2>
                    <p><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${forum.lastUpdateTime}" type="both"/> <span
                            class="pull-right">阅读：${forum.readingNum}</span></p>
                    <hr/>
                    <div style="min-height: 200px;overflow: hidden">
                        ${forum.content}
                    </div>
                </div>

                <div class="row forum_content" style="margin-bottom: 50px">
                    <div class="row">
                        <c:forEach items="${forumComments}" var="item" varStatus="i">
                            <div class="row comment_item_show">
                                <div  style="padding-left: 50px">
                                    <img src="../../${item.user.headImg}" class="user-head"/>
                                    &nbsp;&nbsp;&nbsp;
                                    <span style="font-size: 20px">${item.user.username}</span>
                                    &nbsp;&nbsp;&nbsp;
                                    <span>
                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.forumComment.createDate}"  type="both"/>
                                    </span>

                                    <span class="forum_manager_btn" commentId="${item.forumComment.id}" status="${item.forumComment.status}">
                                        <c:if test="${item.forumComment.status == 1}">
                                            <span class="btn bg-danger btn-sm pull-right" >删除评论</span>
                                        </c:if>
                                        <c:if test="${item.forumComment.status == 2}">
                                            <span class="btn bg-success btn-sm pull-right" >恢复删除</span>
                                        </c:if>
                                    </span>
                                </div>
                                <div class="row">
                                    <p style="padding-left: 100px">${item.forumComment.content}</p>
                                </div>
                                <hr/>
                            </div>
                        </c:forEach>
                        <%--<div class="row" >--%>
                        <%--<div class="row container" style="padding-left: 50px">--%>
                        <%--<img src="../../img/head1.jpeg" class="user-head"/>--%>
                        <%--&nbsp;&nbsp;&nbsp;--%>
                        <%--<span style="font-size: 20px">不好玩</span>--%>
                        <%--&nbsp;&nbsp;&nbsp;--%>
                        <%--<span>2018-05-19 11:48:06</span>--%>
                        <%--</div>--%>
                        <%--<div class="row container">--%>
                        <%--<p style="padding-left: 100px">哈哈哈哈哈哈哈反倒是所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所哈哈哈哈哈哈哈反倒是所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所</p>--%>
                        <%--</div>--%>
                        <%--<hr/>--%>
                        <%--</div>--%>
                    </div>
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