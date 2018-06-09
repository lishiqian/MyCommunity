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
        .head-img-bg{
            height: 100px;
            width: 100px;
            border: solid 2px #2dffc2;
            border-radius: 50%;
        }

    </style>

    <script type="text/javascript">
        $(function () {
            $("#upload_headimg").click(function () {
                $("#headimg_file").click();
            });

            $("#headimg_file").change(function () {
                $.ajax({
                    url: '/user/head_img_upload',　　　　　　　　　　//上传地址
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#uploadForm')[0]),　　　　　　　　　　　　　//表单数据
                    processData: false,
                    contentType: false,
                    success:function(data){
                        $("#show_user_headimg").attr("src",'../../' + data.data);
                    }
                });
            });

            $("#save_edit").click(function () {
                $("#edit_form").submit();
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
                <li class="active"><a href="/user/show_user">我的信息</a></li>
                <li><a href="/forum/forum_list?status=1">我的帖子</a></li>
                <li><a href="/forum/forum_list?status=2">我的草稿</a></li>
                <li><a href="/forum/forum_list?status=3">回收站</a></li>
                <li><a href="/forum/forum_comment_manager_list">评论管理</a></li>
            </ul>
        </div>
        <div class="col-md-9" style="min-height: 500px">
            <h4>基本信息
                <a href="#" id="save_edit" class="btn btn-info pull-right">保存</a>
                <span class="pull-right">&nbsp;&nbsp;&nbsp;</span>
                <a href="/user/show_user" class="btn btn-info pull-right">取消</a></h4>
            <hr/>
            <div class="container-fluid">
                <div class="row">
                    <img id="show_user_headimg" src="../../${login_user.headImg}" class="img-responsive center-block head-img-bg">
                </div>
                <div class="row" style="align-content: center">
                    <p>&nbsp;</p>
                    <center>
                        <span id="upload_headimg" class="button btn-info btn-lg" style="cursor: hand">
                            <span class="glyphicon glyphicon-pencil glyphicon-plus"> 更改头像</span>
                        </span>
                    </center>
                    <hr />
                    <form id="uploadForm" action="/user/head_img_upload" method="post" enctype="multipart/form-data" style="display: none">
                        <input type="file" name="uploadFile" id="headimg_file" accept="image/gif, image/jpeg, image/jpg, image/png"/>
                        <input type="submit" value="上传"/>
                    </form>
                </div>
                <div class="row">
                    <form action="/user/edit_user" method="post" id="edit_form">
                        <div class="row">
                            <div class="col-md-3"><h3><span class="pull-right text-info">我的邮箱:</span></h3></div>
                            <div class="col-md-6">
                                <h3>${login_user.email}</h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3"><h3><span class="pull-right text-info">我的昵称:</span></h3></div>
                            <div class="col-md-6">
                                <h3><input class="form-control" type="text" name="username" placeholder="请输入昵称" value="${login_user.username}"/></h3>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-3"><h3><span class="pull-right text-info">我的性别:</span></h3></div>
                            <div class="col-md-6">
                                <h3>
                                    <select name="gender"  name="gender" value="${login_user.gender}" class="selectpicker show-tick form-control"  data-width="98%" data-first-option="false"  required data-live-search="true">
                                        <option value="0">保密</option>
                                        <option value="1">男</option>
                                        <option value="2">女</option>
                                    </select>
                                </h3>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div style="height:200px"></div>
    </div>
</div>