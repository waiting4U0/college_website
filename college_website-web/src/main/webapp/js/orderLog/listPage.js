/**
 * listPageJS对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-09-29
 */

var listPageJS = {};

/**
 * 分页;
 * @param pageNum
 */
listPageJS.turnPage = function (pageNum) {
    if (!isNaN(pageNum)) {
        listPageJS.getPageList("/orderLog/list", pageNum * 1, 100);
    }
};

listPageJS.turnPageCommon = function (url, pageNum, pageSize) {
    if (!isNaN(pageNum)) {
        listPageJS.getPageList(url, pageNum * 1, pageSize * 1);
    }
};

listPageJS.getPageList = function (_url, _page, _pageSize) {
    var jsonData = {
        startIndex: _page,
        pageSize: _pageSize,
        s_channel_name: $("#s_channel_name").val(),
        s_channel_type: $("#s_channel_type").val(),
        s_start_date: $("#s_start_date").val(),
        s_end_date: $("#s_end_date").val()
    };
    main.menuGoTo(_url, jsonData);
};
