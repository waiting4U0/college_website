/**
 * ufaultreportJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var ufaultreportJS = {};

/**
 * 分页;
 * @param pageNum
 */
ufaultreportJS.saveCheck = function(formId)
{
    return true;
};
ufaultreportJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_contact_phone:$("#s_contact_phone").val(),
			s_fault_type:$("#s_fault_type").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/userFaultReport/list",requestData);
};


ufaultreportJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取

	$.ajax({
	    url: '/userFaultReport/getObject',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var object=jQuery.parseJSON(data.data);
						$('#myModal_updateObject').on('show.bs.modal', function () {
							
							$("#feedId","#form_update").val(object.deviceId);	
							$("#contact","#form_update").val(object.contact);	
							$("#contactPhone","#form_update").val(object.contactPhone);	
							$("#faultName","#form_update").val(object.faultName);	
							
							$("#faultDesc","#form_update").val(object.faultDesc);
							$("#regionName","#form_update").val(object.regionName);
							$("#address","#form_update").val(object.address);
							var mideaReturn="成功";
							if(object.reportResult==1)   mideaReturn="成功";
							else  mideaReturn="失败";
							$("#mideaReturn","#form_update").val(mideaReturn);
							$("#commitDate","#form_update").val(object.commitDate);
						});
						$('#myModal_updateObject').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};

ufaultreportJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/userFaultReport/delete',
	            type: 'POST',
	            data: {"id":id,delTag:1},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/userFaultReport/list",requestData);
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
ufaultreportJS.softDel  = function(id) { 
	   bootbox.confirm("确定删除?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						delTag:1
				};
			    $.ajax({
			        url: '/userFaultReport/delete',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/userFaultReport/list",requestData);
						} else {
							bootbox.alert("删除出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
	  
};

ufaultreportJS.getObject  = function(id) {  
		$.ajax({
		    url: '/userFaultReport/getObject',
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

