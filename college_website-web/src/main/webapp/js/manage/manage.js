var itemclick = function () {
    $.get("main/test", function (data,status) {
        $('.layui-tab-item').html(data);
    });

}