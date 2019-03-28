/**
 * padSnJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var manufacturerIpJS = {};

/**
 * 分页;
 * @param pageNum
 */
manufacturerIpJS.saveCheck = function(formId)
{
	//$("input[type='text']","#"+formId).each(function(){
	//	if($(this).val() == "") {
	//		return false;
	//	}
	//});
    return true;
};
manufacturerIpJS.search = function() {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_name:$("#s_name").val(),
			s_ipValue:$("#s_ipValue").val()
			};
	main.menuGoTo("/manufacturerIp/list",requestData);
};
manufacturerIpJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addPadSn').modal('show');
};
manufacturerIpJS.addSave = function (formId) {
	$('#myModal_addPadSn').on('hidden.bs.modal', function () {
		var requestData = {"startIndex": $("#curPageNum").val()};
		main.menuGoTo("/manufacturerIp/list", requestData);
	});
	if (!manufacturerIpJS.saveCheck(formId)) {
		alert("参数不能为空");
		return;
	}
	var paramData = $("#" + formId).serialize();
	$.ajax({
		url: '/manufacturerIp/save',
		type: 'POST',
		data: paramData,
		dataType: 'json',
		success: function (data) {
			if (data.code == 200) {
				bootbox.alert("保存成功", function () {
					$('#myModal_addPadSn').modal('hide');
				});
			} else {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			}
		}
	});
};

manufacturerIpJS.del  = function(id) {
	var url = "/manufacturerIp/delete/" + id;
    bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: url,
	            type: 'POST',
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/manufacturerIp/list",requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
	            }
	        });
	   }
      
   });
  
};
