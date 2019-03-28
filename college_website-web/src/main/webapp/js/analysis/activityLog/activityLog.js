/**
 * activityLogJS 对象
 *  @type {{}}
 *  @author cdzhangjunhao@jd.com
 *  @since 2017-09-27
 */

var activityLogJS = {};

var init_pageSize = 100;

activityLogJS.search = function () {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData = {
        startIndex: 1,
        pageSize: init_pageSize,
        s_activity_id: $("#s_activity_id").val(),
        s_start_date: $("#s_start_date").val(),
        s_end_date: $("#s_end_date").val()
    };

    main.menuGoTo("/activityLog/list", requestData);
};