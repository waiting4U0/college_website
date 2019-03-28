/**
 * upgradeLogJS对象;
 * 
 * @type {{}}
 * @author weijunlong@jd.com
 * @since 2016-11-23
 */

var thirdMenuVideoJS = {};
var initPageSize = 100;

thirdMenuVideoJS.search = function() {
	// 获取数据值1.从tr获取 2.从服务端获取
	var requestData = {
		startIndex : 1,
		pageSize : initPageSize,
		s_menuId : $("#s_menuId").val(),
		s_start_date : $("#s_start_date").val(),
		s_end_date : $("#s_end_date").val()
	};
	main.menuGoTo("/thirdMenuVideo/list", requestData);
};
thirdMenuVideoJS.exportAsExcel = function() {
    $("#exportForm").submit();
};