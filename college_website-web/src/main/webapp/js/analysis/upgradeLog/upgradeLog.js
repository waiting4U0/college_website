/**
 * upgradeLogJS对象;
 * 
 * @type {{}}
 * @author weijunlong@jd.com
 * @since 2016-11-03
 */

var upgradeLogJS = {};
var initPageSize = 100;

upgradeLogJS.search = function() {
	// 获取数据值1.从tr获取 2.从服务端获取
	var requestData = {
		startIndex : 1,
		pageSize : initPageSize,
		padSn : $("#s_padSn").val(),
		appType : $("#s_appType").val(),
		newVersion : $("#s_newVersion").val(),
		upgradeResult : $("#s_upgradeResult").val(),
		s_start_date : $("#s_start_date").val(),
		s_end_date : $("#s_end_date").val()
	};
	main.menuGoTo("/softwareUpgrade/upgradeLog", requestData);
};
upgradeLogJS.exportAsExcel = function() {
    $("#exportForm").submit();
};