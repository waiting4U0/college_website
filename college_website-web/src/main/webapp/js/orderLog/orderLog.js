/**
 * orderLogJS 对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-09-29
 */
var orderLogJS = {};

orderLogJS.search = function () {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData = {
        startIndex: 1,
        s_channel_name: $("#s_channel_name").val(),
        s_channel_type: $("#s_channel_type").val(),
        s_start_date: $("#s_start_date").val(),
        s_end_date: $("#s_end_date").val()
    };
    main.menuGoTo("/orderLog/list", requestData);
};
