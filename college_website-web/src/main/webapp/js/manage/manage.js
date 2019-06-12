var teacherclick = function () {
    $.get("main/test", function (data,status) {
        $('.layui-tab-item').html(data);
    });

}
var messageclick = function () {
    $.get("main/message", function (data,status) {
        $('.layui-tab-item').html(data);
    });

}
var studentMessaGeclick = function () {
    $.get("main/student", function (data,status) {
        $('.layui-tab-item').html(data);
    });

}