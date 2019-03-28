/**
 * tipsJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var tipsJS = {};


tipsJS.saveCheck = function(formId)
{
	
};
tipsJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			s_tipsName:$("#s_tipsName").val(),
			s_tipsType:$("#s_tipsType").val(),
			s_goodsId:$("#s_goodsId").val()
			};
	main.menuGoTo("/tips/list",requestData);
};
tipsJS.addSave =  function(formId){
	$('#myModal_addTips').on('hidden.bs.modal', function () {
		var requestData={"startIndex":1};
		main.menuGoTo("/tips/list",requestData);
	});	
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/tips/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {	            	
			if (data.code == 200) {				
                bootbox.alert("保存成功", function() {
                	$('#myModal_addTips').modal('hide');
                });
			} else {
				bootbox.alert("保存出现异常:"+data.errorMsg);
			}
        }
    });
	

};
tipsJS.update = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取
    var trId="tr_"+key;
	var tipsName=$("td[name='tipsName']",$("#"+trId)).text();
	var tipsType=$("td[name='tipsType']",$("#"+trId)).text();
	var goodsId=$("td[name='goodsId']",$("#"+trId)).text();
	var goodsName=$("td[name='goodsName']",$("#"+trId)).text();
	var cmsKey=$("td[name='cmsKey']",$("#"+trId)).text();

	$('#myModal_updateTips').on('show.bs.modal', function () {
		$("#id","#form_update").val(key);
		$("#tipsName","#form_update").val(tipsName);
		$("#tipsType","#form_update").val(tipsType);
		$("#goodsName","#form_update").val(goodsName);
		$("#cmsKey","#form_update").val(cmsKey);
	});

	$('#myModal_updateTips').modal('show');
};
tipsJS.updateSave = function(formId) {  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateTips').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/tips/list",requestData);
				});
				
				var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/tips/save',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
			                bootbox.alert("保存成功", function() {
			                	$('#myModal_updateTips').modal('hide');
			                });

						} else {
							bootbox.alert("保存出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	   });

};
tipsJS.del  = function(id) { 
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/tips/delete',
	            type: 'POST',
	            data: {"id":id,"startIndex":$("#curPageNum").val()},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/tips/list",requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
	            }
	        });
	   }
      
   }); 
};