// 解析返回url参数
var getParam = function(key) {
    //任意带参数的url
    var url = location.href;
    // 得到问号之后的字符串
    url = url.substring( url.indexOf('?') + 1 );
    // 按照&符号分解字符串为数组
    var params = url.split('&');
    //遍历数组元素
    for (var i = 0;i < params.length;i ++) {
        // 按照等号分解成键值对
        var str = params[i].split('=');
        if (str[0] == key) {
            return str[1];
        }
    }
}


//消息提示映射
var layMsgMapper = {
    "publish_success":"帖子发布成功",
    "save_success":"帖子保存到草稿",
    "delete_success":"删除成功",
    "comment_success":"评论成功"
};



$(function () {
   if(getParam("lay_msg")){
       layer.msg(layMsgMapper[getParam("lay_msg")]);
   }
   if(getParam("open_login")){
       layer.open({
           offset:'150px',
           title:'用户登录',
           type: 2,
           area: ['360px', '330px'],
           skin: 'layui-layer-rim', //加上边框
           content: ['/show_login', 'no']
       });
   }
});

