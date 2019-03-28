/**
 * mbannerJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var mbannerJS = {};

/**
 * 分页;
 * @param pageNum
 */
mbannerJS.saveCheck = function(formId)
{
    return true;
};
mbannerJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_name:$("#s_name").val()
			};
	main.menuGoTo("/menuBanner/list",requestData);
};
mbannerJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addObject').modal('show');
};
mbannerJS.addSave =  function(formId){
	$('#myModal_addObject').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/menuBanner/list",requestData);
	});	
	if(!mbannerJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/menuBanner/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {	            	
			if (data.code == 200) {				
                bootbox.alert("保存成功", function() {
                	$('#myModal_addObject').modal('hide');
                });
			} else {
				bootbox.alert("保存出现异常:"+data.errorMsg);
			}
        }
    });
	

};

mbannerJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取

	$.ajax({
	    url: '/menuBanner/getObject',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var object=jQuery.parseJSON(data.data);
						$('#myModal_updateObject').on('show.bs.modal', function () {
							$("#id","#form_update").val(key);
							$("#title","#form_update").val(object.title);	
							$("#type","#form_update").val(object.type);	
							$("#type","#form_update").change();
							$("#position","#form_update").val(object.position);
							$("#position","#form_update").change();
							$("#coverUrl","#form_update").val(object.coverUrl);	
							$("#linkId","#form_update").val(object.linkId);	
							$("#linkUrl","#form_update").val(object.linkUrl);	
						});
						$('#myModal_updateObject').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};
mbannerJS.updateSave = function(formId) {  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateObject').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/menuBanner/list",requestData);
				});
				if(!mbannerJS.saveCheck(formId)){
					return;
				}
				
				var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/menuBanner/save',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
			                bootbox.alert("保存成功", function() {
			                	$('#myModal_updateObject').modal('hide');
			                });

						} else {
							bootbox.alert("保存出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	   });

};
mbannerJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/menuBanner/delete',
	            type: 'POST',
	            data: {"id":id,delTag:-1},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/menuBanner/list",requestData);
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
mbannerJS.softDel  = function(id) { 
	   bootbox.confirm("确定banner中不展现?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/menuBanner/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuBanner/list",requestData);
						} else {
							bootbox.alert("出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};
/**
 * 软删除重启用
 */
mbannerJS.reUse  = function(id) { 
	   bootbox.confirm("确定banner中展现?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:0
				};
			    $.ajax({
			        url: '/menuBanner/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuBanner/list",requestData);
						} else {
							bootbox.alert("出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};	
mbannerJS.getObject  = function(id) {  
		$.ajax({
		    url: '/menuBanner/getObject',
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

mbannerJS.uploadImg = function(formId,fileId,targetId) {
	iLoading.show();
    $.ajaxFileUpload(
        {
            url: '/upload/uploadImage', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: fileId, //文件上传域的ID
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
