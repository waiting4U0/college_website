/**
 * goodsWListJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var goodsWListJS = {};

/**
 * 分页;
 * @param pageNum
 */
goodsWListJS.saveCheck = function(formId)
{

    return true;
};
goodsWListJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_name:$("#s_name").val(),
			s_categoryId:$("#s_categoryId").val()
			};
	main.menuGoTo("/goodsWList/list",requestData);
};
goodsWListJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	   
	$('#myModal_addGoods').modal('show');
};
goodsWListJS.addSave =  function(formId){
	$('#myModal_addGoods').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/goodsWList/list",requestData);
	});	
	if(!goodsWListJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/goodsWList/save',
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

goodsWListJS.update = function(eventTag,key) {     

};
goodsWListJS.updateSave = function(formId) {  


};
goodsWListJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/goodsWList/delete',
	            type: 'POST',
	            data: {"id":id,"startIndex":$("#curPageNum").val()},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/goodsWList/list",requestData);
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
goodsWListJS.softDel  = function(id) { 
	   bootbox.confirm("确定删除?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/goodsWList/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/goodsWList/list",requestData);
						} else {
							bootbox.alert("删除出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};

goodsWListJS.getGoods  = function(id) {  
		$.ajax({
		    url: '/goodsWList/getGoods',
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

