var smartMessageTemplateJS = {};

smartMessageTemplateJS.saveCheck = function(formId) {
	var text = $("#text", "#" + formId).val();
	if (text == "") {
		alert("消息模板必填");
		return false;
	}

	return true;

};

smartMessageTemplateJS.add = function(formId) {
	$('#myModal_addSmartMessageTemplate').modal('show');
};

smartMessageTemplateJS.addSave = function(formId) {
	$('#myModal_addSmartMessageTemplate').on('hidden.bs.modal', function() {
		var requestData = {
			"startIndex" : $("#curPageNum").val()
		};
		main.menuGoTo("/smartMessageTemplate/list", requestData);
	});
	if (!smartMessageTemplateJS.saveCheck("form_add"))
		return;

	var paramData = $("#" + formId).serialize();
	$.ajax({
		url : '/smartMessageTemplate/save',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				bootbox.alert("保存成功", function() {
					$('#myModal_addSmartMessageTemplate').modal('hide');
				});
			} else {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			}
		}
	});

};

smartMessageTemplateJS.update = function(eventTag, key) {
	// 获取数据值1.从tr获取 2.从服务端获取
	$.ajax({
		url : '/smartMessageTemplate/getSmartMessageTemplateById',
		type : 'GET',
		data : {
			"id" : key
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {

				var returnObj = jQuery.parseJSON(data.data);
				$('#myModal_updateSmartMessageTemplate').on('show.bs.modal',
						function() {
							$("#id", "#form_update").val(key);
							$("#text", "#form_update").val(returnObj.text);
						});
				$('#myModal_updateSmartMessageTemplate').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};

smartMessageTemplateJS.updateSave = function(formId) {
	if (!smartMessageTemplateJS.saveCheck("form_update"))
		return;

	bootbox.confirm("确定修改?", function(result) {
		if (result) {
			// 确实修改了，才进行列表更新
			$('#myModal_updateSmartMessageTemplate').on(
					'hidden.bs.modal',
					function() {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/smartMessageTemplate/list",requestData);
					});

			var paramData = $("#" + formId).serialize();
			$.ajax({
				url : '/smartMessageTemplate/save',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						bootbox.alert("保存成功", function() {
							$('#myModal_updateSmartMessageTemplate').modal('hide');
						});

					} else {
						bootbox.alert("保存出现异常:" + data.errorMsg);
					}
				}
			});
		}
	});

};
smartMessageTemplateJS.del = function(id) {
	bootbox.confirm("确定删除?", function(result) {
		if (result) {
			$.ajax({
				url : '/smartMessageTemplate/' + id,
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
						main.menuGoTo("/smartMessageTemplate/list",requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
				}
			});
		}

	});

};