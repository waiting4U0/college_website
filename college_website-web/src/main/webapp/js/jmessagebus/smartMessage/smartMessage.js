var smartMessageJS = {};

smartMessageJS.saveCheck = function(formId) {
	var system = $("#system", "#" + formId).val();
	var code = $("#code", "#" + formId).val();
	var destination = $("#destination", "#" + formId).val();
	var invocationMode = $("#invocationMode", "#" + formId).val();
	if (system == "") {
		alert("系统标识符必填");
		return false;
	}
	if (code == "") {
		alert("消息类型必填");
		return false;
	}
	if (destination == "") {
		alert("消息目的地必填");
		return false;
	}
	if (invocationMode == "") {
		alert("调用方式必填");
		return false;
	}
	return true;

};

smartMessageJS.add = function(formId) {
	$('#myModal_addSmartMessage').modal('show');
};

smartMessageJS.addSave = function(formId) {
	$('#myModal_addSmartMessage').on('hidden.bs.modal', function() {
		var requestData = {
			"startIndex" : $("#curPageNum").val()
		};
		main.menuGoTo("/smartMessage/list", requestData);
	});
	if (!smartMessageJS.saveCheck("form_add"))
		return;

	var paramData = $("#" + formId).serialize();
	$.ajax({
		url : '/smartMessage/save',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				bootbox.alert("保存成功", function() {
					$('#myModal_addSmartMessage').modal('hide');
				});
			} else {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			}
		}
	});

};

smartMessageJS.update = function(eventTag, key) {
	// 获取数据值1.从tr获取 2.从服务端获取
	$.ajax({
		url : '/smartMessage/getSmartMessageById',
		type : 'GET',
		data : {
			"id" : key
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {

				var returnObj = jQuery.parseJSON(data.data);
				$('#myModal_updateSmartMessage').on(
						'show.bs.modal',
						function() {
							$("#id", "#form_update").val(key);
							$("#system", "#form_update").val(returnObj.system);
							$("#code", "#form_update").val(returnObj.code);
							$("#destination", "#form_update").val(returnObj.destination);
							$("#invocationMode", "#form_update").val(returnObj.invocationMode);
						});
				$('#myModal_updateSmartMessage').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};

smartMessageJS.updateSave = function(formId) {
	if (!smartMessageJS.saveCheck("form_update"))
		return;

	bootbox.confirm("确定修改?", function(result) {
		if (result) {
			// 确实修改了，才进行列表更新
			$('#myModal_updateSmartMessage').on('hidden.bs.modal', function() {
				var requestData = {
					"startIndex" : $("#curPageNum").val()
				};
				main.menuGoTo("/smartMessage/list", requestData);
			});

			var paramData = $("#" + formId).serialize();
			$.ajax({
				url : '/smartMessage/save',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						bootbox.alert("保存成功", function() {
							$('#myModal_updateSmartMessage').modal('hide');
						});

					} else {
						bootbox.alert("保存出现异常:" + data.errorMsg);
					}
				}
			});
		}
	});

};
smartMessageJS.del = function(id) {
	bootbox.confirm("确定删除?", function(result) {
		if (result) {
			$.ajax({
				url : '/smartMessage/' + id,
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
						main.menuGoTo("/smartMessage/list", requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
				}
			});
		}

	});

};