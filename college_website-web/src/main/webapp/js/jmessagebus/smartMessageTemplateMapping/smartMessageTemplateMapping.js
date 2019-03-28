var smartMessageTemplateMappingJS = {};

smartMessageTemplateMappingJS.saveCheck = function(formId) {
	var templateId = $("#templateId", "#" + formId).val();
	var mappingKey = $("#mappingKey", "#" + formId).val();
	var mappingValue = $("#mappingValue", "#" + formId).val();

	if (templateId == "") {
		alert("模板ID必填");
		return false;
	}
	if (mappingKey == "") {
		alert("映射key必填");
		return false;
	}
	if (mappingValue == "") {
		alert("映射value必填");
		return false;
	}
	
	return true;

};

smartMessageTemplateMappingJS.add = function(formId) {
	$('#myModal_addSmartMessageTemplateMapping').modal('show');
};

smartMessageTemplateMappingJS.addSave = function(formId) {
	$('#myModal_addSmartMessageTemplateMapping').on('hidden.bs.modal', function() {
		var requestData = {
			"startIndex" : $("#curPageNum").val()
		};
		main.menuGoTo("/smartMessageTemplateMapping/list", requestData);
	});
	if (!smartMessageTemplateMappingJS.saveCheck("form_add"))
		return;

	var paramData = $("#" + formId).serialize();
	$.ajax({
		url : '/smartMessageTemplateMapping/save',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				bootbox.alert("保存成功", function() {
					$('#myModal_addSmartMessageTemplateMapping').modal('hide');
				});
			} else {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			}
		}
	});

};

smartMessageTemplateMappingJS.update = function(eventTag, key) {
	// 获取数据值1.从tr获取 2.从服务端获取
	$.ajax({
		url : '/smartMessageTemplateMapping/getSmartMessageTemplateMappingById',
		type : 'GET',
		data : {
			"id" : key
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {

				var returnObj = jQuery.parseJSON(data.data);
				$('#myModal_updateSmartMessageTemplateMapping').on(
						'show.bs.modal',
						function() {
							$("#id", "#form_update").val(key);
							$("#templateId", "#form_update").val(returnObj.templateId);
							$("#mappingKey", "#form_update").val(returnObj.mappingKey);
							$("#mappingValue", "#form_update").val(returnObj.mappingValue);
						});
				$('#myModal_updateSmartMessageTemplateMapping').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};

smartMessageTemplateMappingJS.updateSave = function(formId) {
	if (!smartMessageTemplateMappingJS.saveCheck("form_update"))
		return;

	bootbox.confirm("确定修改?", function(result) {
		if (result) {
			// 确实修改了，才进行列表更新
			$('#myModal_updateSmartMessageTemplateMapping').on('hidden.bs.modal',
					function() {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/smartMessageTemplateMapping/list", requestData);
					});

			var paramData = $("#" + formId).serialize();
			$.ajax({
				url : '/smartMessageTemplateMapping/save',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						bootbox.alert("保存成功", function() {
							$('#myModal_updateSmartMessageTemplateMapping').modal('hide');
						});

					} else {
						bootbox.alert("保存出现异常:" + data.errorMsg);
					}
				}
			});
		}
	});

};
smartMessageTemplateMappingJS.del = function(id) {
	bootbox.confirm("确定删除?", function(result) {
		if (result) {
			$.ajax({
				url : '/smartMessageTemplateMapping/' + id,
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
						main.menuGoTo("/smartMessageTemplateMapping/list", requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
				}
			});
		}

	});

};