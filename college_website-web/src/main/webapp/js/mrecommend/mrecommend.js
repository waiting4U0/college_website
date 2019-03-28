/**
 * mrecommendJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var mrecommendJS = {};

/**
 * 分页;
 * @param pageNum
 */
mrecommendJS.saveCheck = function(formId)
{
	var recommendDes=$("#recommendDes","#"+formId).val();
	
	if(recommendDes==""||recommendDes.trim()==""){
		alert("推荐语不能为空!");
		return false;
	}

    return true;
};
mrecommendJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_name:$("#s_name").val()
			};
	main.menuGoTo("/menuRecommend/list",requestData);
};
mrecommendJS.searchSelect= function() { 

	window.location="/menuRecommend/listselect?"+"startIndex=1"+"&s_name="+encodeURI(encodeURI($("#s_name").val()));
}
mrecommendJS.add = function(paramData) {  
	   bootbox.confirm("确定新增?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新	
			    $.ajax({
			        url: '/menuRecommend/save',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
			                bootbox.alert("保存成功", function() { 
			            		var requestData={"startIndex":1};
			            		main.menuGoTo("/menuRecommend/list",requestData);
			                });
						} else {
							bootbox.alert("保存出现异常:"+data.errorMsg);
						}
						
			        }
			    });
		   }
	   });

};

mrecommendJS.update = function(eventTag,key) {    
	//获取数据值1.从tr获取  2.从服务端获取
	$.ajax({
	    url: '/menuRecommend/getObject',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var object=jQuery.parseJSON(data.data);
						$('#myModal_updateObject').on('show.bs.modal', function () {
								$("#recommendDes","#form_update").val(object.recommendDes);	
								$("#id","#form_update").val(key);	
								$("#terminal","#form_update").val(object.terminal);
								$("#terminal","#form_update").change();
						});
						$('#myModal_updateObject').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};
mrecommendJS.updateSave = function(formId) {  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateObject').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/menuRecommend/list",requestData);
				});
				if(!mrecommendJS.saveCheck(formId)){
					return;
				}
				
				var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/menuRecommend/update',
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
mrecommendJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/menuRecommend/delete',
	            type: 'POST',
	            data: {"id":id,delTag:-1},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/menuRecommend/list",requestData);
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
mrecommendJS.softDel  = function(id) { 
	   bootbox.confirm("确定 不推荐?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/menuRecommend/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuRecommend/list",requestData);
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
mrecommendJS.reUse  = function(id) { 
	   bootbox.confirm("确定推荐?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:0
				};
			    $.ajax({
			        url: '/menuRecommend/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuRecommend/list",requestData);
						} else {
							bootbox.alert("出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};	
mrecommendJS.getObject  = function(id) {  
		$.ajax({
		    url: '/menuRecommend/getObject',
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


