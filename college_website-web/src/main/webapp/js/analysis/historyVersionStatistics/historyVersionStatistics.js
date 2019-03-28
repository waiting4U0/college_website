/**
 * historyVersionStatisticsJS对象;
 * 
 * @type {{}}
 * @author weijunlong@jd.com
 * @since 2016-11-10
 */

var historyVersionStatisticsJS = {};
var initPageSize = 100;

historyVersionStatisticsJS.search = function() {
	// 获取数据值1.从tr获取 2.从服务端获取
	var requestData = {
		startIndex : 1,
		pageSize : initPageSize,
		s_uuid : $("#s_uuid").val(),
		s_appType : $("#s_appType").val(),
		s_newVersion : $("#s_newVersion").val(),
		s_start_date : $("#s_start_date").val(),
		s_end_date : $("#s_end_date").val()
	};
	main.menuGoTo("/historyVersionStatistics/list", requestData);
};
historyVersionStatisticsJS.exportAsExcel = function() {
    $("#exportForm").submit();
};