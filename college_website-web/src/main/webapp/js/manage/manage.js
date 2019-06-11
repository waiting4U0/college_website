var itemclick = function () {
    $.get("main/test", function (data,status) {
        $('.layui-tab-item').html(data);
    });

}
var messageclick = function () {
    $.get("main/message", function (data,status) {
        $('.layui-tab-item').html(data);
    });

}