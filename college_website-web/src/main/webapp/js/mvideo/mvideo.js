/**
 * mvideoJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var mvideoJS = {};

/**
 * 分页;
 * @param pageNum
 */
mvideoJS.saveCheck = function(formId)
{
	var title=$("#title","#"+formId).val();
	var source=$("#source","#"+formId).val();
	var coverUrl=$("#coverUrl","#"+formId).val();
	var videoCode=$("#videoCode","#"+formId).val();
	
	if(title==""){
		alert("视频名称必填!");
		return false;
	}
	if(source==""){
		alert("视频来源必填!");
		return false;
	}
	if(coverUrl==""){
		alert("视频封面必填!");
		return false;
	}
	if(videoCode==""){
		alert("视频代码必填!");
		return false;
	}
	return true;
};
mvideoJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_name:$("#s_name").val()
			};
	main.menuGoTo("/menuVideo/list",requestData);
};
mvideoJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addObject').modal('show');
};
mvideoJS.addSave =  function(formId){
	$('#myModal_addObject').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/menuVideo/list",requestData);
	});	
	if(!mvideoJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/menuVideo/save',
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

mvideoJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取

	$.ajax({
	    url: '/menuVideo/getObject',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var object=jQuery.parseJSON(data.data);
						$('#myModal_updateObject').on('show.bs.modal', function () {
							$("#id","#form_update").val(key);
							$("#title","#form_update").val(object.title);	
							$("#source","#form_update").val(object.source);	
							$("#coverUrl","#form_update").val(object.coverUrl);	
							$("#videoCode","#form_update").val(object.videoCode);	
							
							$("#videoType","#form_update").val(object.videoType);
							$("#videoType","#form_update").change();
						});
						$('#myModal_updateObject').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};
mvideoJS.updateSave = function(formId) {  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateObject').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/menuVideo/list",requestData);
				});
				if(!mvideoJS.saveCheck(formId)){
					return;
				}
				
				var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/menuVideo/save',
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
mvideoJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/menuVideo/delete',
	            type: 'POST',
	            data: {"id":id,delTag:1},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/menuVideo/list",requestData);
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
mvideoJS.softDel  = function(id) { 
	   bootbox.confirm("确定删除?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/menuVideo/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuVideo/list",requestData);
						} else {
							bootbox.alert("删除出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};
/**
 * 软删除重启用
 */
mvideoJS.reUse  = function(id) { 
	   bootbox.confirm("确定启用?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:0
				};
			    $.ajax({
			        url: '/menuVideo/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuVideo/list",requestData);
						} else {
							bootbox.alert("启用出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};	
mvideoJS.getObject  = function(id) {  
		$.ajax({
		    url: '/menuVideo/getObject',
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

mvideoJS.uploadImg = function(formId,fileId,targetId) {
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
