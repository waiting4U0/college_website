/**
 * deviceHardwareJS对象;
 * @type {{}}
 * @author weijunlong
 * @since 2016-12-16
 */
var deviceHardwareJS = {};

/**
 * 分页;
 * @param pageNum
 */
deviceHardwareJS.saveCheck = function(formId)
{

    return true;
};
deviceHardwareJS.search = function() {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_uuid:$("#s_uuid").val()
			};
	main.menuGoTo("/devicehardware/list",requestData);
};
deviceHardwareJS.add =  function(formId){

	$("input[type='text']","#"+formId).each(function(){	
		$(this).val("");
	});
	$("select","#"+formId).each(function(){
		$(this).val("0");
	});
	   
	$('#myModal_addDeviceHardware').modal('show');
};
deviceHardwareJS.addSave =  function(formId){
	$('#myModal_addDeviceHardware').on('hidden.bs.modal', function () {
		var requestData={"startIndex":$("#curPageNum").val()};
		main.menuGoTo("/devicehardware/list",requestData);
	});
	if(!deviceHardwareJS.saveCheck(formId)){
		return;
	}
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/devicehardware/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {	            	
			if (data.code == 200) {				
                bootbox.alert("保存成功", function() {
					$('#myModal_addDeviceHardware').modal('hide');
                });
			} else {
				bootbox.alert("保存出现异常:"+data.errorMsg);
			}
        }
    });
	

};

deviceHardwareJS.update = function(eventTag,key) {
	//获取数据值1.从tr获取  2.从服务端获取
	$.ajax({
	    url: '/devicehardware/getDeviceHardware',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {
					if (data.code == 200) {
	                    var returnObj=jQuery.parseJSON(data.data);
						$('#myModal_updateDeviceHardware').on('show.bs.modal', function () {
							$("#id","#form_update").val(key);
							$("#productUuid","#form_update").val(returnObj.productUuid);
							$("#brand","#form_update").val(returnObj.brand);
							$("#model","#form_update").val(returnObj.model);
							$("#rearCamera","#form_update").val(returnObj.rearCamera);
							$("#frontCamera","#form_update").val(returnObj.frontCamera);
							$("#infraredSensor","#form_update").val(returnObj.infraredSensor);
							$("#doorStructure","#form_update").val(returnObj.doorStructure);
							$("#controlPage","#form_update").val(returnObj.controlPage);
							$("#bugReport","#form_update").val(returnObj.bugReport);

						});
						$('#myModal_updateDeviceHardware').modal('show');
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};


deviceHardwareJS.updateSave = function(formId) {
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
			   $('#myModal_updateDeviceHardware').on('hidden.bs.modal', function () {
				   var requestData={"startIndex":$("#curPageNum").val()};
				   main.menuGoTo("/devicehardware/list",requestData);
			   });
			   if(!deviceHardwareJS.saveCheck(formId)){
				   return;
			   }

			   var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/devicehardware/save',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {
						if (data.code == 200) {
			                bootbox.alert("保存成功", function() {
			                	$('#myModal_updateDeviceHardware').modal('hide');
			                });

						} else {
							bootbox.alert("保存出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	   });

};

deviceHardwareJS.del  = function(id) {
	var url = "/devicehardware/delete/" + id;
    bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: url,
	            type: 'POST',
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/devicehardware/list",requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
	            }
	        });
	   }
      
   });
  
};

