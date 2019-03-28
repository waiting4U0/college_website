/**
 * realTimeVersionStatisticsJS对象;
 * 
 * @type {{}}
 * @author weijunlong@jd.com
 * @since 2016-11-09
 */

var realTimeVersionStatisticsJS = {};
var initPageSize = 100;

realTimeVersionStatisticsJS.search = function() {
	// 获取数据值1.从tr获取 2.从服务端获取
	var requestData = {
		startIndex : 1,
		pageSize : initPageSize,
		s_uuid : $("#s_uuid").val(),
		s_appType : $("#s_appType").val()
	};
	main.menuGoTo("/realTimeVersionStatistics/list", requestData);
};
realTimeVersionStatisticsJS.exportAsExcel = function() {
    $("#exportForm").submit();
};