/**
 * analysisdevctlJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var analysisdevctlJS = {};
var init_pageSize=100;
/**
 * 分页;
 * @param pageNum
 */
analysisdevctlJS.saveCheck = function(formId)
{

    return true;
};
analysisdevctlJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_uuid:$("#s_uuid").val(),
			s_city:$("#s_city").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/analysisdevctl/listdevctl",requestData);
};

analysisdevctlJS.view = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取

	$.ajax({
	    url: '/analysisdevctl/viewdevctl',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var object=jQuery.parseJSON(data.data);
						$('#myModal_updateObject').on('show.bs.modal', function () {
							$("#id","#form_view").val(key);
							$("#title","#form_view").val(object.title);				
						});
						$('#myModal_viewObject').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};



