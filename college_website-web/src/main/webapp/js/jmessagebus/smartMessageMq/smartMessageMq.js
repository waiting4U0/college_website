var smartMessageMqJS = {};

smartMessageMqJS.saveCheck = function(formId) {
	var messageId = $("#messageId", "#" + formId).val();
	var templateId = $("#templateId", "#" + formId).val();
	var address = $("#address", "#" + formId).val();
	var user = $("#user", "#" + formId).val();
	var password = $("#password", "#" + formId).val();
	var app = $("#app", "#" + formId).val();
	var retryTimes = $("#retryTimes", "#" + formId).val();
	var topic = $("#topic", "#" + formId).val();
	if (messageId == "") {
		alert("消息ID必填");
		return false;
	}
	if (templateId == "") {
		alert("模板ID必填");
		return false;
	}
	if (address == "") {
		alert("JMQ客户端地址必填");
		return false;
	}
	if (user == "") {
		alert("用户名必填");
		return false;
	}
	if (password == "") {
		alert("密码必填");
		return false;
	}
	if (app == "") {
		alert("主题名称必填");
		return false;
	}
	if (retryTimes == "") {
		alert("重试次数必填");
		return false;
	}
	if (topic == "") {
		alert("主题代码topic必填");
		return false;
	}
	return true;

};

smartMessageMqJS.add = function(formId) {
	$('#myModal_addSmartMessageMq').modal('show');
};

smartMessageMqJS.addSave = function(formId) {
	$('#myModal_addSmartMessageMq').on('hidden.bs.modal', function() {
		var requestData = {
			"startIndex" : $("#curPageNum").val()
		};
		main.menuGoTo("/smartMessageMq/list", requestData);
	});
	if (!smartMessageMqJS.saveCheck("form_add"))
		return;

	var paramData = $("#" + formId).serialize();
	$.ajax({
		url : '/smartMessageMq/save',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				bootbox.alert("保存成功", function() {
					$('#myModal_addSmartMessageMq').modal('hide');
				});
			} else {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			}
		}
	});

};


smartMessageMqJS.update = function(eventTag, key) {
	// 获取数据值1.从tr获取 2.从服务端获取
	$.ajax({
		url : '/smartMessageMq/getSmartMessageMqById',
		type : 'GET',
		data : {
			"id" : key
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {

				var returnObj = jQuery.parseJSON(data.data);
				$('#myModal_updateSmartMessageMq').on(
						'show.bs.modal',
						function() {
							$("#id", "#form_update").val(key);
							$("#messageId", "#form_update").val(returnObj.messageId);
							$("#templateId", "#form_update").val(returnObj.templateId);
							$("#address", "#form_update").val(returnObj.address);
							$("#user", "#form_update").val(returnObj.user);
							$("#password", "#form_update").val(returnObj.password);
							$("#app", "#form_update").val(returnObj.app);
							$("#retryTimes", "#form_update").val(returnObj.retryTimes);
							$("#topic", "#form_update").val(returnObj.topic);
						});
				$('#myModal_updateSmartMessageMq').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};

smartMessageMqJS.updateSave = function(formId) {
	if (!smartMessageMqJS.saveCheck("form_update"))
		return;

	bootbox.confirm("确定修改?", function(result) {
		if (result) {
			// 确实修改了，才进行列表更新
			$('#myModal_updateSmartMessageMq').on('hidden.bs.modal', function() {
				var requestData = {
					"startIndex" : $("#curPageNum").val()
				};
				main.menuGoTo("/smartMessageMq/list", requestData);
			});

			var paramData = $("#" + formId).serialize();
			$.ajax({
				url : '/smartMessageMq/save',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						bootbox.alert("保存成功", function() {
							$('#myModal_updateSmartMessageMq').modal('hide');
						});

					} else {
						bootbox.alert("保存出现异常:" + data.errorMsg);
					}
				}
			});
		}
	});

};
smartMessageMqJS.del = function(id) {
	bootbox.confirm("确定删除?", function(result) {
		if (result) {
			$.ajax({
				url : '/smartMessageMq/' + id,
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
						main.menuGoTo("/smartMessageMq/list", requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
				}
			});
		}

	});

};