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
			s_city:$("#s_city").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/analysisRecognition/list",requestData);
};

analysisRecognitionJS.export = function() {
	$("#form_export").submit();
};


