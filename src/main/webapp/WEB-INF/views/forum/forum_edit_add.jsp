<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的博客</title>
    <!-- 引入jquery的js文件 -->
    <script type="text/javascript" src="../../util/jquery/jquery-3.3.1.min.js"></script>
    <!-- 引入bootstrap的js文件 -->
    <script type="text/javascript" src="../../util/bootstrap/js/bootstrap.js"></script>
    <!-- 引入bootstrap的css文件 -->
    <link rel="stylesheet" type="text/css" href="../../util/bootstrap/css/bootstrap.css">
    <!-- 配置文件 -->
    <script type="text/javascript" src="../../util/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../../util/ueditor/ueditor.all.js"></script>

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
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        $(function () {
            var ue = UE.getEditor('myEditor', {
                initialFrameHeight: 600,//设置编辑器高度
                scaleEnabled: true,//设置不自动调整高度 scaleEnabled {Boolean} [默认值：false]//是否可以拉伸长高，(设置true开启时，自动长高失效)
                wordCount: false //关闭字数统计
            });

            var commit = function (status) {
                var content = ue.getContent();
                var title = $("#title").val();

                var prim = {
                    title: title,
                    content: content,
                    status: status
                };

                $.post("/forum/update_forum", prim, function (data) {
                    if(status == 1) {
                        window.location.href = "/forum/forum_list?status=1&lay_msg=publish_success";
                    }else if(status == 2){
                        window.location.href = "/forum/forum_list?status=2&lay_msg=save_success";
                    }
                })
            }

            $("#save").click(function () {
                commit(2);
            });

            $("#publish").click(function () {
                commit(1)
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
            <div class="button btn-info btn-lg pull-right">
                <span class="glyphicon glyphicon-plus">写博客</span>
            </div>
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
                <li class="active"><a href="/forum/forum_list?status=1">我的文章</a></li>
                <li><a href="/forum/forum_list?status=2">我的草稿</a></li>
                <li><a href="/forum/forum_list?status=3">回收站</a></li>
                <li><a href="#">评论管理</a></li>
            </ul>
        </div>
        <div class="col-md-9" style="min-height: 500px">
            <h4>编写博客</h4>
            <hr/>
            <div class="row">
                <input type="text" class="form-control" placeholder="请输入标题" id="title"/>
            </div>
            <div class="row">&nbsp;</div>
            <div class="row">
                <!-- 加载编辑器的容器 -->
                <textarea id="myEditor" name="content" type="text/plain"></textarea>
            </div>
            <div class="row">&nbsp;</div>
            <div class="row">&nbsp;</div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-4">
                    <button type="button" class="btn btn-info" id="save">保存草稿</button>
                    </p>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-4">
                    <button type="button" class="btn btn-danger" id="publish">发布文章</button>
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