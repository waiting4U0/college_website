/**
 * ufeedbackJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var ufeedbackJS = {};

/**
 * 分页;
 * @param pageNum
 */
ufeedbackJS.saveCheck = function(formId)
{
    return true;
};
ufeedbackJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取

	var requestData={
			startIndex:1,
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/userFeedBack/list",requestData);
};


ufeedbackJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取

	$.ajax({
	    url: '/userFeedBack/getObject',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var object=jQuery.parseJSON(data.data);
						$('#myModal_updateObject').on('show.bs.modal', function () {
							$("#userPin","#form_update").val(object.userPin);	
							$("#content","#form_update").val(object.content);								
						});
						$('#myModal_updateObject').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};

ufeedbackJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/userFeedBack/delete',
	            type: 'POST',
	            data: {"id":id,delTag:1},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/userFeedBack/list",requestData);
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
ufeedbackJS.softDel  = function(id) { 
	   bootbox.confirm("确定删除?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/userFeedBack/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/userFeedBack/list",requestData);
						} else {
							bootbox.alert("删除出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};

ufeedbackJS.getObject  = function(id) {  
		$.ajax({
		    url: '/userFeedBack/getObject',
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

