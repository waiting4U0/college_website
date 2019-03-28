/**
 * mhotsearchJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var mhotsearchJS = {};

/**
 * 分页;
 * @param pageNum
 */
mhotsearchJS.saveCheck = function(formId)
{
    return true;
};
mhotsearchJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_name:$("#s_name").val()
			};
	main.menuGoTo("/menuHotSearch/list",requestData);
};
mhotsearchJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addObject').modal('show');
};
mhotsearchJS.addSave =  function(formId){
	$('#myModal_addObject').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/menuHotSearch/list",requestData);
	});	
	if(!mhotsearchJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/menuHotSearch/save',
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

mhotsearchJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取

	$.ajax({
	    url: '/menuHotSearch/getObject',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var object=jQuery.parseJSON(data.data);
						$('#myModal_updateObject').on('show.bs.modal', function () {
							$("#id","#form_update").val(key);
							$("#name","#form_update").val(object.name);	
						});
						$('#myModal_updateObject').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};
mhotsearchJS.updateSave = function(formId) {  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateObject').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/menuHotSearch/list",requestData);
				});
				if(!mhotsearchJS.saveCheck(formId)){
					return;
				}
				
				var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/menuHotSearch/save',
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
mhotsearchJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/menuHotSearch/delete',
	            type: 'POST',
	            data: {"id":id,"startIndex":$("#curPageNum").val()},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/menuHotSearch/list",requestData);
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
mhotsearchJS.softDel  = function(id) { 
	   bootbox.confirm("确定删除?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/menuHotSearch/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuHotSearch/list",requestData);
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
mhotsearchJS.reUse  = function(id) { 
	   bootbox.confirm("确定启用?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:0
				};
			    $.ajax({
			        url: '/menuHotSearch/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuHotSearch/list",requestData);
						} else {
							bootbox.alert("启用出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};	
mhotsearchJS.getObject  = function(id) {  
		$.ajax({
		    url: '/menuHotSearch/getObject',
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


