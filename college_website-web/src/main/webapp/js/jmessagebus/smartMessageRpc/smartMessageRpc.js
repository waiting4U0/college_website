var smartMessageRpcJS = {};

smartMessageRpcJS.saveCheck = function(formId) {
	var messageId = $("#messageId", "#" + formId).val();
	var interfaceName = $("#interfaceName", "#" + formId).val();
	var retries = $("#retries", "#" + formId).val();
	var registry = $("#registry", "#" + formId).val();
	var protocol = $("#protocol", "#" + formId).val();
	var alias = $("#alias", "#" + formId).val();
	var methodName = $("#methodName", "#" + formId).val();
	var paramsType = $("#paramsType", "#" + formId).val();
	var params = $("#params", "#" + formId).val();
	if (messageId == "") {
		alert("消息ID必填");
		return false;
	}
	if (interfaceName == "") {
		alert("接口名称必填");
		return false;
	}
	if (retries == "") {
		alert("重试次数必填");
		return false;
	}
	if (registry == "") {
		alert("注册地址必填");
		return false;
	}
	if (protocol == "") {
		alert("服务分类必填");
		return false;
	}
	if (alias == "") {
		alert("组名必填");
		return false;
	}
	if (methodName == "") {
		alert("方法名称必填");
		return false;
	}
	if (paramsType == "") {
		alert("参数类型必填");
		return false;
	}
	if (params == "") {
		alert("参数映射必填");
		return false;
	}
	return true;

};

smartMessageRpcJS.add = function(formId) {
	$('#myModal_addSmartMessageRpc').modal('show');
};

smartMessageRpcJS.addSave = function(formId) {
	$('#myModal_addSmartMessageRpc').on('hidden.bs.modal', function() {
		var requestData = {
			"startIndex" : $("#curPageNum").val()
		};
		main.menuGoTo("/smartMessageRpc/list", requestData);
	});
	if (!smartMessageRpcJS.saveCheck("form_add"))
		return;

	var paramData = $("#" + formId).serialize();
	$.ajax({
		url : '/smartMessageRpc/save',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				bootbox.alert("保存成功", function() {
					$('#myModal_addSmartMessageRpc').modal('hide');
				});
			} else {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			}
		}
	});

};

smartMessageRpcJS.update = function(eventTag, key) {
	// 获取数据值1.从tr获取 2.从服务端获取
	$.ajax({
		url : '/smartMessageRpc/getSmartMessageRpcById',
		type : 'GET',
		data : {
			"id" : key
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {

				var returnObj = jQuery.parseJSON(data.data);
				$('#myModal_updateSmartMessageRpc').on(
						'show.bs.modal',
						function() {
							$("#id", "#form_update").val(key);
							$("#messageId", "#form_update").val(returnObj.messageId);
							$("#interfaceName", "#form_update").val(returnObj.interfaceName);
							$("#retries", "#form_update").val(returnObj.retries);
							$("#registry", "#form_update").val(returnObj.registry);
							$("#protocol", "#form_update").val(returnObj.protocol);
							$("#alias", "#form_update").val(returnObj.alias);
							$("#methodName", "#form_update").val(returnObj.methodName);
							$("#paramsType", "#form_update").val(returnObj.paramsType);
							$("#params", "#form_update").val(returnObj.params);
						});
				$('#myModal_updateSmartMessageRpc').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};

smartMessageRpcJS.updateSave = function(formId) {
	if (!smartMessageRpcJS.saveCheck("form_update"))
		return;

	bootbox.confirm("确定修改?", function(result) {
		if (result) {
			// 确实修改了，才进行列表更新
			$('#myModal_updateSmartMessageRpc').on('hidden.bs.modal',
					function() {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/smartMessageRpc/list", requestData);
					});

			var paramData = $("#" + formId).serialize();
			$.ajax({
				url : '/smartMessageRpc/save',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						bootbox.alert("保存成功", function() {
							$('#myModal_updateSmartMessageRpc').modal('hide');
						});

					} else {
						bootbox.alert("保存出现异常:" + data.errorMsg);
					}
				}
			});
		}
	});

};
smartMessageRpcJS.del = function(id) {
	bootbox.confirm("确定删除?", function(result) {
		if (result) {
			$.ajax({
				url : '/smartMessageRpc/' + id,
				type : 'DELETE',
				data : {
					"startIndex" : $("#curPageNum").val()
				},
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/smartMessageRpc/list", requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
				}
			});
		}

	});

};