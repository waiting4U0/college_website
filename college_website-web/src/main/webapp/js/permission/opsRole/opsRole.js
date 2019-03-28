
var opsRoleJS = {};

/**
 * 分页;
 * @param pageNum
 */
opsRoleJS.saveCheck = function(formId)
{
	var opsRoleName=$("#opsRoleName","#"+formId).val();
	var opsRoleUrl=$("#opsRoleUrl","#"+formId).val();
	var opsResouceKey=$("#opsResouceKey","#"+formId).val();
	var opsResouceIcon=$("#opsResouceIcon","#"+formId).val();
	var opsRoleParentId=$("#opsRoleParentId","#"+formId).val();
	if (opsRoleParentId != "") {
		if(opsResouceKey=="" || opsRoleUrl==""){
			alert("子菜单key和url参数必填");
			return false;
		}
	}
	if(opsRoleName=="" || opsResouceIcon==""){
		alert("参数必填");
		return false;
	}
	return true;
	
};

opsRoleJS.add =  function(formId){
	$("input[type='text']","#"+formId).each(function(){	
        $(this).val("");
	});
	$.ajax({
		url: '/opsRole/getResourceList/0',
		type: 'GET',
		//data: {"id":key},
		dataType: 'json',
		success: function (data) {
			if (data.code == 200) {
				var returnObj=data.data;
				$('#myModal_addOpsRole').on('show.bs.modal', function () {
					var element = $("#check_group", "#form_add");
					element.empty();
					for (var i = 0; i < returnObj.length; i++){

						if (returnObj[i].parentId == null) {
							element.append('<div class="form-group" style="padding-top: 0px;"></div>');
							element.append('<label class="btn-block" style="font-weight:bold;height:13px;"><input id="check_parent_'+ returnObj[i].id +'" onchange="opsRoleJS.chooseAll(this,'+ returnObj[i].id +')" name="opsResource"  type="checkbox" value="'+ returnObj[i].id +'"> '+ returnObj[i].name +' </label>');
							continue;
						}
						element.append('<label class="checkbox-inline">' +
						'<input id="check_'+ returnObj[i].parentId +
						'" name="opsResource" onchange="opsRoleJS.chooseOne(this,'+ returnObj[i].parentId +')" type="checkbox" value="'+ returnObj[i].id +'"> '
						+ returnObj[i].name +'</label>');
					}
				});
				$('#myModal_addOpsRole').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
	$('#myModal_addOpsRole').modal('show');
};

opsRoleJS.chooseAll = function(eventTag,id){
	//$("#check_" + id, ".checkbox-inline").each(function(){
	//	if($(this).prop("checked")) {
	//		$(this).prop("checked", false);
	//	} else {
	//		$(this).prop("checked", true);
	//	}
	//});
	if($(eventTag).prop("checked")) {
		$("#check_" + id, ".checkbox-inline").prop("checked", true);
	} else {
		$("#check_" + id, ".checkbox-inline").prop("checked", false);
	}
};

opsRoleJS.chooseOne = function(eventTag,id){

	if($(eventTag).prop("checked")) {
		$("#check_parent_" + id, ".btn-block").prop("checked", true);
	} else {
		var count = 0;
		$("#check_" + id, ".checkbox-inline").each(function(){
			if($(this).prop("checked")) {
				count ++;
			}
		});
		if(count > 0) {
			$("#check_parent_" + id, ".btn-block").prop("checked", true);
		}else {
			$("#check_parent_" + id, ".btn-block").prop("checked", false);
		}
	}
};

opsRoleJS.addSave =  function(formId){
	$('#myModal_addOpsRole').on('hidden.bs.modal', function () {
		var requestData={"startIndex":$("#curPageNum").val()};

		main.menuGoTo("/opsRole/list",requestData);
	});	
	if(!opsRoleJS.saveCheck("form_add")) return;
	
	var paramData=$("#"+formId).serialize();
    $.ajax({
        url: '/opsRole/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {	            	
			if (data.code == 200) {				
                bootbox.alert("保存成功", function() {
                	$('#myModal_addOpsRole').modal('hide');
                });
			} else {
				bootbox.alert("保存出现异常:"+data.errorMsg);
			}
        }
    });
};

opsRoleJS.update = function(eventTag,key) {
	//获取数据值1.从tr获取  2.从服务端获取
    var trId="tr_"+key;

	var opsRoleName=$("td[name='ops_role_name']",$("#"+trId)).text();
	var opsRoleDescription=$("td[name='ops_role_description']",$("#"+trId)).text();

	$("#opsRoleId","#form_update").val(key);
	$("#opsRoleName","#form_update").val(opsRoleName);
	$("#opsRoleDescription","#form_update").val(opsRoleDescription);

	$.ajax({
		url: '/opsRole/getResourceList/' + key,
		type: 'GET',
		//data: {"id":key},
		dataType: 'json',
		success: function (data) {
			if (data.code == 200) {
				var returnObj=data.data;
				$('#myModal_updateOpsRole').on('show.bs.modal', function () {
					var element = $("#check_group", "#form_update");
					element.empty();
					for (var i = 0; i < returnObj.length; i++){
						var isChecked = returnObj[i].checked == "1" ? "checked" : "";
						if (returnObj[i].parentId == null) {
							element.append('<div class="form-group" style="padding-top: 0px;"></div>')
							element.append('<label class="btn-block" style="font-weight:bold;height:13px;"><input id="check_parent_'+ returnObj[i].id +'" onchange="opsRoleJS.chooseAll(this,'+ returnObj[i].id +')" name="opsResource" '+ isChecked +' type="checkbox" value="'+ returnObj[i].id +'"> '+ returnObj[i].name +' </label>');
							continue;
						}
						element.append('<label class="checkbox-inline"> <input id="check_'+ returnObj[i].parentId +'" onchange="opsRoleJS.chooseOne(this,'+ returnObj[i].parentId +')" name="opsResource" '+ isChecked +' type="checkbox" value="'+ returnObj[i].id +'"> '+ returnObj[i].name +'</label>');

					}
				});
				$('#myModal_updateOpsRole').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};
opsRoleJS.updateSave = function(formId) {
	  if(!opsRoleJS.saveCheck("form_update")) return;
	  
	   bootbox.confirm("确定修改?", function(result) {
		   if(result){
			   //确实修改了，才进行列表更新
				$('#myModal_updateOpsRole').on('hidden.bs.modal', function () {
					var requestData={"startIndex":$("#curPageNum").val()};
					main.menuGoTo("/opsRole/list",requestData);
				});
				var paramData=$("#"+formId).serialize();
			    $.ajax({
			        url: '/opsRole/save',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
			                bootbox.alert("保存成功", function() {
			                	$('#myModal_updateOpsRole').modal('hide');
			                });

						} else {
							bootbox.alert(data.errorMsg);
						}
			        }
			    });
		   }
	   });

};
opsRoleJS.del  = function(id) {
   bootbox.confirm("确定删除?", function(result) {
	   if(result){		    
	        $.ajax({
	            url: '/opsRole/'+id,
	            type: 'DELETE',
	            data: {"categoryId":id,"startIndex":$("#curPageNum").val()},
	            dataType: 'json',
	            success: function (data) {	            	
					if (data.code == 200) {
						var requestData={"startIndex":$("#curPageNum").val()};
						main.menuGoTo("/opsRole/list",requestData);
					} else {
						bootbox.alert(data.errorMsg);
					}
	            }
	        });
	   }
      
   });
  
};


