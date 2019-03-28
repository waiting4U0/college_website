/**
 * categoryJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var categoryJS = {};

/**
 * 分页;
 * @param pageNum
 */
categoryJS.saveCheck = function(formId)
{
	var name=$("#name","#"+formId).val();
	var iorder=$("#iorder","#"+formId).val();
	if(name==""){
		alert("名字必填!");
		return false;
	}
	if(iorder==""){
		alert("显示顺序必填!");
		return false;
	}
	return true;
	
};

categoryJS.add =  function(formId){
	$("input[type='text']","#"+formId).each(function(){	
        $(this).val("");
	});
	$('#myModal_addCategory').modal('show');
};
categoryJS.addSave =  function(formId){
	$('#myModal_addCategory').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/category/list",requestData);
	});	
	if(!categoryJS.saveCheck("form_add")) return;
	
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/category/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {	            	
			if (data.code == 200) {				
                bootbox.alert("保存成功", function() {
                	$('#myModal_addCategory').modal('hide');
                });
			} else if(data.code == 50001){
				bootbox.alert("保存出现异常:"+data.errorMsg);
			} else if(data.code == 50002){
				bootbox.alert("保存出现异常:"+data.errorMsg);
			} else {
				bootbox.alert("保存出现异常:"+data.errorMsg);
			}
        }
    });
	

};
categoryJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取
    var trId="tr_"+key;
	var name=$("td[name='name']",$("#"+trId)).text();
	var imgUrl=$("td[name='imgUrl']",$("#"+trId)).text();
	var iorder=$("td[name='iorder']",$("#"+trId)).text();

	$('#myModal_updateCategory').on('show.bs.modal', function () {
		$("#categoryId","#form_update").val(key);
		$("#categoryId","#form_update").attr("accessKey", key);
		$("#name","#form_update").val(name);	
		$("#imgUrl","#form_update").val(imgUrl);
		$("#iorder","#form_update").val(iorder);
	});

	$('#myModal_updateCategory').modal('show');
};
categoryJS.updateSave = function(formId) {  
	  if(!categoryJS.saveCheck("form_update")) return;
	  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateCategory').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/category/list",requestData);
				});
				
				var historyId = $("#categoryId", "#form_update").attr("accessKey");
				$("#historyId").val(historyId);
				var paramData = $("#" + formId).serialize();
				
			    $.ajax({
			        url: '/category/update',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
			                bootbox.alert("保存成功", function() {
			                	$('#myModal_updateCategory').modal('hide');
			                });

						} else {
							bootbox.alert("保存出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	   });

};
categoryJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/category/delete',
	            type: 'POST',
	            data: {"categoryId":id,"startIndex":$("#curPageNum").val()},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/category/list",requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
	            }
	        });
	   }
      
   });
  
};
/**
 * 软删除
 */
categoryJS.softDel  = function(id) { 
	   bootbox.confirm("确定删除?", function(result) {
		   if(result){		    
				var paramData={
						categoryId:id,
						delTag:1
				};
			    $.ajax({
			        url: '/category/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/category/list",requestData);
						} else {
							bootbox.alert("删除出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
	};
/**
 * 软删除后重新启用
 */
categoryJS.reUse  = function(id) { 
	   bootbox.confirm("确定启用?", function(result) {
		   if(result){		    
				var paramData={
						categoryId:id,
						delTag:0
				};
			    $.ajax({
			        url: '/category/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/category/list",requestData);
						} else {
							bootbox.alert("启用出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
	};


categoryJS.uploadImg = function(formId,fileId,targetId) {
	iLoading.show();
    $.ajaxFileUpload(
            {
                url: '/upload/uploadImage', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: fileId, //文件上传域的ID另外后台action中的参数  @RequestParam MultipartFile uploadFile 要和form中input name一致
                dataType: 'json', //返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                	iLoading.close();
                	$("#"+targetId,$("#"+formId)).val(data.url);
                    if (typeof (data.error) != 'undefined') {
                        if (data.error != 0) {
                        	bootbox.alert("图片上传错误:"+data.message);
                        } else {
                        	bootbox.alert(data.message);
                        }
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                	iLoading.close();
                	bootbox.alert("图片上传错误:"+e);
                }
            }
        );
    return false;
};
