/**
 * analysisRecognitionJS;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var analysisRecognitionJS = {};
var init_pageSize=100;
/**
 * 分页;
 * @param pageNum
 */
analysisRecognitionJS.saveCheck = function(formId)
{

    return true;
};
analysisRecognitionJS.search = function() {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_uuid:$("#s_uuid").val(),
			s_pad_sn:$("#s_pad_sn").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/analysisRecognition/deviceFoodAnalysisList",requestData);
};

analysisRecognitionJS.toFeedIdDetail = function(s_date_time, s_feed_id, s_pad_sn) {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
		startIndex:1,
		pageSize:init_pageSize,
		s_start_date:$("#s_start_date").val(),
		s_end_date:$("#s_end_date").val(),
		source_page_num:$("#curPageNum").val(),//查询源页面的当前页数
		s_date_time:s_date_time,
		s_feed_id:s_feed_id,
		s_pad_sn:s_pad_sn,
		condition_pad_sn:$("#s_pad_sn").val()
	};
	main.menuGoTo("/analysisRecognition/toGoodsIdDetailPage",requestData);
};

analysisRecognitionJS.export = function() {
	$("#form_export").submit();
};


//$("#s_food_name").bind('input propertychange', function() {
//	//searchProductClassbyName();
//	//var inputWord = $("#s_food_name").val();
//	//var myReg = /^[\u4e00-\u9fa5]+$/;
//	//if(myReg.test(inputWord)){
//	//	alert("汉字");
//	//	return;
//	//}
//	alert(11);
//
//});




