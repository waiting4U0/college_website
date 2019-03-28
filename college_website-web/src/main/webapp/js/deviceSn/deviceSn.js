/**
 * deviceSnJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var deviceSnJS = {};

/**
 * 分页;
 * @param pageNum
 */
deviceSnJS.saveCheck = function(formId)
{

    return true;
};
deviceSnJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_feedId:$("#s_feedId").val(),
			s_productUuid:$("#s_productUuid").val(),
			s_sn:$("#s_sn").val()
			};
	main.menuGoTo("/deviceSn/list",requestData);
};
deviceSnJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addDeviceSn').modal('show');
};
deviceSnJS.addSave =  function(formId){
	$('#myModal_addDeviceSn').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/deviceSn/list",requestData);
	});	
	if(!deviceSnJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/deviceSn/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {	            	
			if (data.code == 200) {				
                bootbox.alert("保存成功", function() {
                	$('#myModal_addDeviceSn').modal('hide');
                });
			} else {
				bootbox.alert("保存出现异常:"+data.errorMsg);
			}
        }
    });
};

deviceSnJS.cleanRevertPin = function() {
	if ($("#btCleanRevertPin").val() == "清除") {
		$("#activatePin").val("");
		$("#btCleanRevertPin").val("恢复");
	}
	else {
		$("#activatePin").val($("#hdPin").val());
		$("#btCleanRevertPin").val("清除");
	}
}

deviceSnJS.cleanRevertFeedId = function() {
	if ($("#btCleanRevertFeedId").val() == "清除") {
		$("#feedId").val("");
		$("#btCleanRevertFeedId").val("恢复");
	}
	else {
		$("#feedId").val($("#hdFeedId").val());
		$("#btCleanRevertFeedId").val("清除");
	}
}

deviceSnJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取
	$.ajax({
	    url: '/deviceSn/getDeviceSn',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {
	                    var returnObj=JSON.parse(data.data);
						$('#myModal_updateDeviceSn').on('show.bs.modal', function () {
							
							$("#id","#form_update").val(key);
							$("#productUuid","#form_update").val(returnObj.productUuid);	
							$("#deviceId","#form_update").val(returnObj.deviceId);
							$("#activatePin","#form_update").val(returnObj.activatePin);
							$("#hdPin","#form_update").val(returnObj.activatePin);
							$("#feedId","#form_update").val(returnObj.strFeedId);
							$("#hdFeedId","#form_update").val(returnObj.strFeedId);
							$("#activateTime","#form_update").val(returnObj.activateTime);							
						});  
						$('#myModal_updateDeviceSn').modal('show');
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};
deviceSnJS.updateSave = function(formId) {  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateDeviceSn').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/deviceSn/list",requestData);
				});
				if(!deviceSnJS.saveCheck(formId)){
					return;
				}
				
				var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/deviceSn/save',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
			                bootbox.alert("保存成功", function() {
			                	$('#myModal_updateDeviceSn').modal('hide');
			                });

						} else {
							bootbox.alert("保存出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	   });

};
deviceSnJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/deviceSn/' + id,
	            type: 'DELETE',
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/deviceSn/list",requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
	            }
	        });
	   }
      
   });
  
};

deviceSnJS.getGoods  = function(id) {  
		$.ajax({
		    url: '/deviceSn/getDeviceSn',
		    type: 'GET',
		    data: {"id":id},
		    dataType: 'json',
		    success: function (data) {	            	
						if (data.code == 200) {
                            return data.data;
						} else {
							bootbox.alert("获取数据错误!");
							return null;
						}
		    }
		});
};


deviceSnJS.importExcel =  function(formId){
	$('#myModal_importExcel').modal('show');
};



deviceSnJS.uploadExcel = function(formId,fileId,targetId) {

	
	iLoading.show();
	var fileUploadParam01 = $("#productUuid",$("#"+formId)).val();
    $.ajaxFileUpload(
            {
                url: '/deviceSn/importExcle', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: fileId, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                data: {//加入的文本参数  
                    "productUuid": fileUploadParam01
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
                        		main.menuGoTo("/deviceSn/list",requestData);
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

