/**
 * analysisactivatehistoryJS对象
 *  @type {{}}
 *  @author weijunlong@jd.com
 *  @since 2017-08-02
 */

var analysisactivatehistoryJS = {};
var init_pageSize=100;

analysisactivatehistoryJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_pad_sn:$("#s_pad_sn").val(),
			s_uuid:$("#s_uuid").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	
	main.menuGoTo("/analysisactivateHistory/list",requestData);
};