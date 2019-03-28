/**
 * analysisOutStockJS;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var analysisOutStockJS = {};
var init_pageSize=100;
/**
 * 分页;
 * @param pageNum
 */
analysisOutStockJS.saveCheck = function(formId)
{

    return true;
};
analysisOutStockJS.search = function() {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_uuid:$("#s_uuid").val(),
			s_food_name:$("#s_food_name").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/analysisOutStock/getOutStockList",requestData);
};

analysisOutStockJS.toFeedIdDetail = function(s_date_time) {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
		startIndex:1,
		pageSize:init_pageSize,
		s_uuid:$("#s_uuid").val(),
		s_food_name:$("#s_food_name").val(),
		s_start_date:$("#s_start_date").val(),
		s_end_date:$("#s_end_date").val(),
		source_page_num:$("#curPageNum").val(),//查询源页面的当前页数
		s_date_time:s_date_time
	};
	main.menuGoTo("/analysisOutStock/toFeedIdDetailPage",requestData);
};

analysisOutStockJS.export = function() {
	$("#form_export").submit();
};


