/**
 * faultReportJS对象;
 * 
 * @type {{}}
 * @author weijunlong@jd.com
 * @since 2016-11-18
 */

var faultReportJS = {};
var initPageSize = 10;

faultReportJS.search = function() {
	// 获取数据值1.从tr获取 2.从服务端获取
	var requestData = {
		startIndex : 1,
		pageSize : initPageSize,
		s_padSn : $("#s_padSn").val(),
		s_deviceSn : $("#s_deviceSn").val(),
		s_start_date : $("#s_start_date").val(),
		s_end_date : $("#s_end_date").val()
	};
	main.menuGoTo("/repair/faultReport", requestData);
};