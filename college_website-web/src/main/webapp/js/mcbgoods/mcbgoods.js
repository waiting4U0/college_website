/**
 * mcbgoodsJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var mcbgoodsJS = {};

/**
 * 分页;
 * @param pageNum
 */
mcbgoodsJS.saveCheck = function(formId)
{
    return true;
};
mcbgoodsJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_name:$("#s_name").val()
			};
	main.menuGoTo("/menuCBGoods/list",requestData);
};
mcbgoodsJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addGoods').modal('show');
};
mcbgoodsJS.addSave =  function(formId){
	$('#myModal_addGoods').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/menuCBGoods/list",requestData);
	});	
	if(!mcbgoodsJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/menuCBGoods/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {	            	
			if (data.code == 200) {				
                bootbox.alert("保存成功", function() {
                	$('#myModal_addGoods').modal('hide');
                });
			} else {
				bootbox.alert("保存出现异常:"+data.errorMsg);
			}
        }
    });
	

};

mcbgoodsJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取

	$.ajax({
	    url: '/menuCBGoods/getGoods',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var goods=jQuery.parseJSON(data.data);
						$('#myModal_updateGoods').on('show.bs.modal', function () {
							$("#id","#form_update").val(key);
							$("#name","#form_update").val(goods.name);	
						});
						$('#myModal_updateGoods').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};
mcbgoodsJS.updateSave = function(formId) {  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateGoods').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/menuCBGoods/list",requestData);
				});
				if(!mcbgoodsJS.saveCheck(formId)){
					return;
				}
				
				var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/menuCBGoods/save',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
			                bootbox.alert("保存成功", function() {
			                	$('#myModal_updateGoods').modal('hide');
			                });

						} else {
							bootbox.alert("保存出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	   });

};
mcbgoodsJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/menuCBGoods/delete',
	            type: 'POST',
	            data: {"id":id,"startIndex":$("#curPageNum").val()},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/menuCBGoods/list",requestData);
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
mcbgoodsJS.softDel  = function(id) { 
	   bootbox.confirm("确定删除?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/menuCBGoods/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuCBGoods/list",requestData);
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
mcbgoodsJS.reUse  = function(id) { 
	   bootbox.confirm("确定启用?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:0
				};
			    $.ajax({
			        url: '/menuCBGoods/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuCBGoods/list",requestData);
						} else {
							bootbox.alert("启用出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};	
mcbgoodsJS.getGoods  = function(id) {  
		$.ajax({
		    url: '/menuCBGoods/getGoods',
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


