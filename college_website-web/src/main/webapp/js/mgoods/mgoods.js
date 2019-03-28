/**
 * mgoodsJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var mgoodsJS = {};

/**
 * 分页;
 * @param pageNum
 */
mgoodsJS.saveCheck = function(formId)
{

    return true;
};
mgoodsJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_name:$("#s_name").val(),
			s_categoryId:$("#s_categoryId").val()
			};
	main.menuGoTo("/menuGoods/list",requestData);
};
mgoodsJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addGoods').modal('show');
};
mgoodsJS.addSave =  function(formId){
	$('#myModal_addGoods').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/menuGoods/list",requestData);
	});	
	if(!mgoodsJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/menuGoods/save',
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

mgoodsJS.update = function(eventTag,key) {     

};
mgoodsJS.updateSave = function(formId) {  


};
mgoodsJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/menuGoods/delete',
	            type: 'POST',
	            data: {"id":id,"startIndex":$("#curPageNum").val()},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/menuGoods/list",requestData);
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
mgoodsJS.softDel  = function(id) { 
	   bootbox.confirm("确定删除?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/menuGoods/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/menuGoods/list",requestData);
						} else {
							bootbox.alert("删除出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};

mgoodsJS.getGoods  = function(id) {  
		$.ajax({
		    url: '/menuGoods/getGoods',
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

