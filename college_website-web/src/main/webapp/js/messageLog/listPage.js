/**
 * listPageJS对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-09-05
 */

var listPageJS = {};

/**
 * 分页;
 * @param pageNum
 */
listPageJS.turnPage = function (pageNum) {
    if (!isNaN(pageNum)) {
        listPageJS.getPageList("/messageLog/list", pageNum * 1, 100);
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
        s_title:$("#s_title").val(),
        s_typeCode:$("#s_typeCode").val(),
        s_padSn:$("#s_padSn").val(),
        s_messageState:$("#s_messageState").val(),
        s_isRead:$("#s_isRead").val(),
        s_startDate:$("#s_startDate").val(),
        s_endDate:$("#s_endDate").val()
    };
    main.menuGoTo(_url, jsonData);
};
