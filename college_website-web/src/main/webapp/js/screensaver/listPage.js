/**
 * listPageJS对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-08-22
 */

var listPageJS = {};

/**
 * 分页;
 * @param pageNum
 */
listPageJS.turnPage = function (pageNum) {
    if (!isNaN(pageNum)) {
        listPageJS.getPageList("/screensaver/list", pageNum * 1, 20);
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
        s_productUuid: $("#s_productUuid").val()
    };
    main.menuGoTo(_url, jsonData);
};
