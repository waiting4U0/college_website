/**
 * Created by cdshenjian on 2016/11/3.
 */
var jdbeanJS = {};
var init_pageSize = 100;

jdbeanJS.search = function() {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData={
        startIndex:1,
        pageSize:init_pageSize,
        s_uuid:$("#s_uuid").val(),
        s_pin:$("#s_pin").val(),
        s_start_date:$("#s_start_date").val(),
        s_end_date:$("#s_end_date").val()
    };
    main.menuGoTo("/jdbean/list",requestData);
};

jdbeanJS.export = function() {
    $("#form_export").submit();
}