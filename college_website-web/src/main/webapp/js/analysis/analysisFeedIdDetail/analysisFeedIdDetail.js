/**
 * analysisFeedIdDetailJS;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var analysisFeedIdDetailJS = {};
var init_pageSize=100;
/**
 * 分页;
 * @param pageNum
 */
analysisFeedIdDetailJS.export = function(request_url)
{
	var form = $("<form>");
	form.attr('method','post');
	form.attr('action',request_url);

	var input1 = $('<input>');
	input1.attr('type','hidden');
	input1.attr('name','s_uuid');
	input1.attr('value',$("#s_uuid").val());

	var input2 = $('<input>');
	input2.attr('type','hidden');
	input2.attr('name','s_city');
	input2.attr('value',$("#s_city").val());

	var input3 = $('<input>');
	input3.attr('type','hidden');
	input3.attr('name','s_start_date');
	input3.attr('value',$("#s_start_date").val());

	var input4 = $('<input>');
	input4.attr('type','hidden');
	input4.attr('name','s_end_date');
	input4.attr('value',$("#s_end_date").val());

	var input5 = $('<input>');
	input5.attr('type','hidden');
	input5.attr('name','source_page_num');
	input5.attr('value',$("#source_page_num").val());

	var input6 = $('<input>');
	input6.attr('type','hidden');
	input6.attr('name','s_food_name');
	input6.attr('value',$("#s_food_name").val());

	var input7 = $('<input>');
	input7.attr('type','hidden');
	input7.attr('name','s_date_time');
	input7.attr('value',$("#s_date_time").val());

	form.append(input1);
	form.append(input2);
	form.append(input3);
	form.append(input4);
	form.append(input5);
	form.append(input6);
	form.append(input7);
	$("body").append(form);
	form.submit();
};
analysisFeedIdDetailJS.returnToPage = function() {
	//获取数据值1.从tr获取  2.从服务端获取
	var url = $("#return_url").val();
	var requestData={
		startIndex:$("#source_page_num").val(),
		pageSize:init_pageSize,
		s_uuid:$("#s_uuid").val(),
		s_city:$("#s_city").val(),
		s_food_name:$("#s_food_name").val(),
		s_start_date:$("#s_start_date").val(),
		s_end_date:$("#s_end_date").val()
	};
	main.menuGoTo(url,requestData);
};



