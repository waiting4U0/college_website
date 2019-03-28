/**
 * Created by cdshenjian on 2016/11/3.
 */
var closedoorJS = {};
var init_pageSize = 100;

closedoorJS.search = function() {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData={
        startIndex:1,
        pageSize:init_pageSize,
        s_uuid:$("#txt_uuid").val(),
        s_city:$("#txt_city").val(),
        s_start_date:$("#txt_start_date").val(),
        s_end_date:$("#txt_end_date").val()
    };
    main.menuGoTo("/closedoorstatistics/list",requestData);
};

closedoorJS.export = function() {
    $("#form1 input[name='s_uuid']").val($("#txt_uuid").val());
    $("#form1 input[name='s_city']").val($("#txt_city").val());
    $("#form1 input[name='s_start_date']").val($("#txt_start_date").val());
    $("#form1 input[name='s_end_date']").val($("#txt_end_date").val());
    $("#form1").submit();
};