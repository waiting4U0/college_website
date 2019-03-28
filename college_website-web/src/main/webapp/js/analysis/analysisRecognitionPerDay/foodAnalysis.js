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
			s_food_name:$("#s_food_name").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/analysisRecognition/foodAnalysisList",requestData);
};

analysisRecognitionJS.toFeedIdDetail = function(s_date_time) {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
		startIndex:1,
		pageSize:init_pageSize,
		s_uuid:$("#s_uuid").val(),
		s_city:$("#s_city").val(),
		s_food_name:$("#s_food_name").val(),
		s_start_date:$("#s_start_date").val(),
		s_end_date:$("#s_end_date").val(),
		source_page_num:$("#curPageNum").val(),//查询源页面的当前页数
		s_date_time:s_date_time
	};
	main.menuGoTo("/analysisRecognition/toFeedIdDetailPage",requestData);
};

analysisRecognitionJS.export = function() {
	$("#form_export").submit();
};

analysisRecognitionJS.valueChange = function () {
	var s_food_name = $("#s_food_name").val();
	if (s_food_name == "" || s_food_name == null){
		return;
	}
	var paramData = {inputName:s_food_name};

	var goodsNameList = $("#food_name_list");
	goodsNameList.empty();
	$.ajax({
		url: '/analysisRecognition/getFoodNameList',
		type: 'POST',
		data: paramData,
		dataType: 'json',
		success: function (data) {
			if (data.code == 200) {
				var names = data.data;
				for (var i = 0; i < names.length; i++){
					goodsNameList.append('<a href="javascript:;" class="list-group-item" onclick="analysisRecognitionJS.selectFoodName(this);">'+ names[i] +'</a>');
				}
				$("#food_name_list").css({'display':''});
			} else {
				bootbox.alert("获取食物名称异常");
			}
		}
	});

};

analysisRecognitionJS.selectFoodName = function(e) {

	$("#s_food_name").val($(e).text());
	$("#food_name_list").css({'display':'none'});
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




