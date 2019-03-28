/**
 * couponJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var couponJS = {};
var init_pageSize=100;
/**
 * 分页;
 * @param pageNum
 */
couponJS.saveCheck = function(formId)
{

    return true;
};
couponJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_user_pin:$("#s_user_pin").val(),
			s_coupon_id:$("#s_coupon_id").val(),
			s_op_type:$("#s_op_type").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/coupon/list",requestData);
};


