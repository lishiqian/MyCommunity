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



$(function () {
   if(getParam("lay_msg")){
       layer.msg(getParam("lay_msg"));
   }
});