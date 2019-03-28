/**
 * padSnJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var padSnJS = {};

/**
 * 分页;
 * @param pageNum
 */
padSnJS.saveCheck = function(formId)
{

    return true;
};
padSnJS.search = function() {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_feedId:$("#s_feedId").val(),
			s_bindPin:$("#s_bindPin").val(),
			s_sn:$("#s_sn").val()
			};
	main.menuGoTo("/padSn/list",requestData);
};
padSnJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addPadSn').modal('show');
};
padSnJS.addSave =  function(formId){
	$('#myModal_addPadSn').on('hidden.bs.modal', function () {
		var requestData={"startIndex":$("#curPageNum").val()};
		main.menuGoTo("/padSn/list",requestData);
	});
	if(!padSnJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/padSn/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {	            	
			if (data.code == 200) {				
                bootbox.alert("保存成功", function() {
					$('#myModal_addPadSn').modal('hide');
                });
			} else {
				bootbox.alert("保存出现异常:"+data.errorMsg);
			}
        }
    });
	

};

padSnJS.update = function(eventTag,key) {
	//获取数据值1.从tr获取  2.从服务端获取
	$.ajax({
	    url: '/padSn/getPadSn',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {
					if (data.code == 200) {
	                    var returnObj=jQuery.parseJSON(data.data);
						$('#myModal_updatePadSn').on('show.bs.modal', function () {
							$("#id","#form_update").val(key);
							$("#productUuid","#form_update").val(returnObj.productUuid);
							$("#padSn","#form_update").val(returnObj.padSn);
							$("#mac","#form_update").val(returnObj.mac);
							$("#feedId","#form_update").val(returnObj.strFeedId);
							$("#hdFeedId","#form_update").val(returnObj.strFeedId);
							$("#bindPin","#form_update").val(returnObj.bindPin);
							$("#hdBindPin","#form_update").val(returnObj.bindPin);
						});
						$('#myModal_updatePadSn').modal('show');
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};

padSnJS.cleanRevert = function() {
	if ($("#btCleanRevert").val() == "清除") {
		$("#feedId").val("");
		$("#btCleanRevert").val("恢复");
	}
	else {
		$("#feedId").val($("#hdFeedId").val());
		$("#btCleanRevert").val("清除");
	}
}

padSnJS.unbindOrRevert = function() {
	if ($("#btUnbind").val() == "解绑") {
		$("#bindPin").val("");
		$("#btUnbind").val("恢复");
	}
	else {
		$("#bindPin").val($("#hdBindPin").val());
		$("#btUnbind").val("解绑");
	}
};

padSnJS.updateSave = function(formId) {
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
			   $('#myModal_updatePadSn').on('hidden.bs.modal', function () {
				   var requestData={"startIndex":$("#curPageNum").val()};
				   main.menuGoTo("/padSn/list",requestData);
			   });
			   if(!padSnJS.saveCheck(formId)){
				   return;
			   }

			   var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/padSn/save',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {
						if (data.code == 200) {
			                bootbox.alert("保存成功", function() {
			                	$('#myModal_updatePadSn').modal('hide');
			                });

						} else {
							bootbox.alert("保存出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	   });

};

padSnJS.del  = function(id) {
	var url = "/padSn/delete/" + id;
    bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: url,
	            type: 'POST',
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/padSn/list",requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
	            }
	        });
	   }
      
   });
  
};


padSnJS.importExcel =  function(formId){
	$('#myModal_importExcel').modal('show');
};


padSnJS.uploadExcel = function(formId,fileId,targetId) {


	iLoading.show();
    $.ajaxFileUpload(
            {
                url:'/padSn/importExcle', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: fileId, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                data: {
					isTestDevice : $('input[name="isTestDevice"]:checked').val()
                },
                success: function (data, status)  //服务器成功响应处理函数
                {
                	iLoading.close();
                	$("#"+targetId,$("#"+formId)).val(data.url);
                    if (typeof (data.error) != 'undefined') {
                        if (data.error != 0) {
                        	bootbox.alert("错误:"+data.message);
                        } else {
                        	$('#myModal_importExcel').on('hidden.bs.modal', function () {
                        		var requestData={"startIndex":1};
                        		main.menuGoTo("/padSn/list",requestData);
                        	});

                        	bootbox.alert(data.message);
                        }
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                	iLoading.close();
                	bootbox.alert("错误:"+e);
                }
            }
        );
    return false;
};
