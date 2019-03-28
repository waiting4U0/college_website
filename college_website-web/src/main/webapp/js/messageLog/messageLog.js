/**
 * messageLogJS 对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-09-05
 */
var messageLogJS = {};

messageLogJS.search = function () {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData = {
        startIndex: 1,
        s_title:$("#s_title").val(),
        s_typeCode:$("#s_typeCode").val(),
        s_padSn:$("#s_padSn").val(),
        s_messageState:$("#s_messageState").val(),
        s_isRead:$("#s_isRead").val(),
        s_startDate:$("#s_startDate").val(),
        s_endDate:$("#s_endDate").val()
    };
    main.menuGoTo("/messageLog/list", requestData);
};
