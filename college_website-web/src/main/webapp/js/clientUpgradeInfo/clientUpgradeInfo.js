var clientUpgradeInfoJS = {};

/**
 * 分页;
 * 
 * @param pageNum
 */
clientUpgradeInfoJS.saveCheck = function(formId) {
	var url = $("#url", "#" + formId).val();
	var size = $("#size", "#" + formId).val();
	var appName = $("#appName", "#" + formId).val();
	var appType =  $("#appType", "#" + formId).val();
	var platform = $("#platform", "#" + formId).val();
	var padsnUrl = $("#padsnUrl", "#" + formId).val();
	var productUuid = $("#productUuid", "#" + formId).val();
	var description = $("#description", "#" + formId).val();
	var upgradeRange = $("#upgradeRange", "#" + formId).val();
	var versionNumber = $("#versionNumber", "#" + formId).val();
	var platformVerDes = $("#platformVerDes", "#" + formId).val();

	url = $.trim(url);
	size = $.trim(size);
	appName = $.trim(appName);
	appType = $.trim(appType);
	padsnUrl = $.trim(padsnUrl);
	productUuid = $.trim(productUuid);
	description = $.trim(description);
	versionNumber = $.trim(versionNumber);
	platformVerDes = $.trim(platformVerDes);

	if (!url || url == "") {
		alert("安装包下载地址必填!");
		return false;
	}

	if (!size || size == "") {
		alert("包的大小必填!");
		return false;
	}

	var reg = new RegExp("apk$");
	if (platform == 'android') {
		if (!reg.test(url)) {
			alert("包的下载地址只能上传apk格式的文件!");
			return false;
		}
	}
	if (appName == 'mApp') {
		if(platformVerDes == "") {
			alert("应用名称选择手机App,系统说明必填！");
			return false;
		}
	}

	var regExp = new RegExp("txt$");
	if (padsnUrl != "" && !regExp.test(padsnUrl)) {
		alert("设备序列号只能上传txt格式的文件!");
		return false;
	}

	if (appType == 'pad' &&(!productUuid || productUuid == "")) {
		alert("UUID必填!");
		return false;
	}

	var reg = new RegExp('^[0-9]+\.+[0-9]+\.[0-9]+$');
	if (!versionNumber || versionNumber == "") {
		alert("版本号必填!");
		return false;
	}
	if (!reg.test(versionNumber)) {
		alert("XX.XX.XX，XX是数字，位数不用限制");
		return false;
	}
	
	if(upgradeRange == 1){
		padsnUrl = "";
	}
	
	//2：黑名单，3：白名单
	if(upgradeRange == 2 || upgradeRange ==3){
		if(!padsnUrl || padsnUrl == ""){
			alert("部分升级，设备序列号url不能为空!");
			return false;
		}
	}
	
	return true;
};

clientUpgradeInfoJS.search = function() {
	// 获取数据值1.从tr获取 2.从服务端获取
	var requestData = {
		startIndex : 1,
		s_productUuid : $("#s_productUuid").val(),
		s_appName : $("#s_appName").val()
	};
	main.menuGoTo("/clientUpgrade/list", requestData);
};

clientUpgradeInfoJS.add = function(formId) {
	// $("input[type='text']","#"+formId).each(function(){
	// $(this).val("");
	// });

	$('#myModal_addClientUpgradeInfo').modal('show');
};

clientUpgradeInfoJS.addSave = function(formId) {
	$('#myModal_addClientUpgradeInfo').on('hidden.bs.modal', function() {
		var requestData = {
			"startIndex" : 1
		};
		main.menuGoTo("/clientUpgrade/list", requestData);
	});
	if (!clientUpgradeInfoJS.saveCheck(formId)) {
		return;
	}
	var paramData = $("#" + formId).serialize();
	$.ajax({
		url : '/clientUpgrade/save',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				bootbox.alert("保存成功", function() {
					$('#myModal_addClientUpgradeInfo').modal('hide');
				});
			} else {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			}
		}
	});
};

clientUpgradeInfoJS.update = function(eventTag, key) {
	// 获取数据值1.从tr获取 2.从服务端获取
	$.ajax({
		url : '/clientUpgrade/getClientUpgradeInfoById',
		type : 'GET',
		data : {
			"id" : key
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {

				var returnObj = jQuery.parseJSON(data.data);
				$('#myModal_updateClientUpgradeInfo').on(
						'show.bs.modal',
						function() {
							$("#id", "#form_update").val(key);
							$("#productUuid", "#form_update").val(returnObj.productUuid);
							clientUpgradeInfoJS.updateChange("form_update",returnObj.platform);
							$("#platform", "#form_update").val(returnObj.platform);
							$("#platformVerDes", "#form_update").val(returnObj.platformVerDes);
							$("#appType", "#form_update").val(returnObj.appType);
							$("#appName", "#form_update").val(returnObj.appName);
							$("#versionNumber", "#form_update").val(returnObj.versionNumber);
							$("#isForcedUpgrade", "#form_update").val(returnObj.isForcedUpgrade);
							$("#md5", "#form_update").val(returnObj.md5);
							$("#upgradeRange", "#form_update").val(returnObj.upgradeRange);
							$("#upgradeRange", "#form_update").change();
							$("#padsnUrl", "#form_update").val(returnObj.padsnUrl);
							$("#isPush", "#form_update").val(returnObj.isPush);
							$("#url", "#form_update").val(returnObj.url);
							$("#size", "#form_update").val(returnObj.size);
							$("#description", "#form_update").val(returnObj.description);
							//如果是ios,修改时直接隐藏uuid
							if(returnObj.appType == "app"){
								$("[name=productUuid_2]").parents(".form-group").addClass("dpn");
							}
							else
							{
								$("[name=productUuid_2]").parents(".form-group").removeClass("dpn");
							}
						});
				$("#b_save").show();
				$('#myModal_updateClientUpgradeInfo').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};

clientUpgradeInfoJS.updateSave = function(formId) {
	bootbox.confirm("确定修改?", function(result) {
		if (result) {
			// 确实修改了，才进行列表更新
			$('#myModal_updateClientUpgradeInfo').on('hidden.bs.modal',
					function() {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/clientUpgrade/list", requestData);
					});
			if (!clientUpgradeInfoJS.saveCheck(formId)) {
				return;
			}
			var paramData = $("#" + formId).serialize();
			$.ajax({
				url : '/clientUpgrade/save',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						bootbox.alert("保存成功",function() {
									$('#myModal_updateClientUpgradeInfo').modal('hide');
								});

					} else {
						bootbox.alert("保存出现异常:" + data.errorMsg);
					}
				}
			});
		}
	});
};

clientUpgradeInfoJS.del = function(id) {
	bootbox.confirm("确定删除?", function(result) {
		if (result) {
			$.ajax({
				url : '/clientUpgrade/' + id,
				type : 'DELETE',
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/clientUpgrade/list", requestData);
					} else {
						bootbox.alert("删除出现异常，请重新删除!");
					}
				}
			});
		}
	});
};

clientUpgradeInfoJS.ajaxFileUpload = function(id) {
	$("#uploading", "#form_add").show();
	$("#uploading", "#form_update").show();
	$.ajaxFileUpload({
		url : '/clientUpgrade/upLoadFile', // 需要链接到服务器地址
		secureuri : false,
		fileElementId : id, // 文件选择框的id属性
		dataType : 'json', // 服务器返回的格式，可以是json, xml
		success : function(data) // 相当于java中try语句块的用法
		{
			if (data.code == 200) {
				var url = data.data.path;
				url = url.replace("amp;", "");
				url = url.replace("amp;", "");
				url = url.replace("amp;", "");
				url = url.replace("amp;", "");
				url = url.replace("amp;", "");
				// var download_url = url.replace("360buyimg","jd");
				var download_url = url;
				// $("#url").val(download_url);
				$("#size", "#form_add").val(data.data.size);
				$("#size", "#form_update").val(data.data.size);
				$("#url", "#form_add").val(download_url);
				$("#url", "#form_update").val(download_url);
				$("#md5", "#form_add").val(data.data.md5);
				$("#md5", "#form_update").val(data.data.md5);
				$("#versionNumber", "#form_add").val(data.data.apk_version);
				$("#versionNumber", "#form_update").val(data.data.apk_version);
				// $("#fileName").val(data.data.name);
				$("#uploading", "#form_add").hide();
				$("#uploading", "#form_update").hide();
			} else {
				bootbox.alert("上传失败");
				$("#uploading", "#form_add").hide();
				$("#uploading", "#form_update").hide();
			}
		},
		error : function() // 相当于java中catch语句块的用法
		{
			bootbox.alert("上传出现错误");
			$("#uploading", "#form_add").hide();
			$("#uploading", "#form_update").hide();
		}
	});
};

/**
 * 上传padSn
 */

clientUpgradeInfoJS.ajaxPadSnUpload = function(id) {
	$("#uploadingPadSn", "#form_add").show();
	$("#uploadingPadSn", "#form_update").show();
	$.ajaxFileUpload({
		url : '/clientUpgrade/upLoadPadSn', // 需要链接到服务器地址
		secureuri : false,
		fileElementId : id, // 文件选择框的id属性
		dataType : 'json', // 服务器返回的格式，可以是json, xml
		success : function(data) // 相当于java中try语句块的用法
		{
			if (data.code == 200) {
				var url = data.data.path;
				url = url.replace("amp;", "");
				url = url.replace("amp;", "");
				url = url.replace("amp;", "");
				url = url.replace("amp;", "");
				url = url.replace("amp;", "");
				// var download_url = url.replace("360buyimg","jd");
				var download_url = url;
				$("#padsnUrl", "#form_add").val(download_url);
				$("#padsnUrl", "#form_update").val(download_url);
				$("#uploadingPadSn", "#form_add").hide();
				$("#uploadingPadSn", "#form_update").hide();
			} else {
				bootbox.alert("上传失败");
				$("#uploadingPadSn", "#form_add").hide();
				$("#uploadingPadSn", "#form_update").hide();
			}
		},
		error : function() // 相当于java中catch语句块的用法
		{
			bootbox.alert("上传出现错误");
			$("#uploadingPadSn", "#form_add").hide();
			$("#uploadingPadSn", "#form_update").hide();
		}
	});
};

clientUpgradeInfoJS.show = function(id) {
	$.ajax({
		url : '/clientUpgrade/getClientUpgradeInfoById',
		type : 'GET',
		data : {
			"id" : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {

				var returnObj = jQuery.parseJSON(data.data);
				$('#myModal_updateClientUpgradeInfo').on(
						'show.bs.modal',
						function() {
							$("#id", "#form_update").val(key);
							$("#productUuid", "#form_update").val(returnObj.productUuid);
							$("#platform", "#form_update").val(returnObj.platform);
							$("#platformVerDes", "#form_update").val(returnObj.platformVerDes);
							$("#appType", "#form_update").val(returnObj.appType);
							$("#appName", "#form_update").val(returnObj.appName);
							$("#versionNumber", "#form_update").val(returnObj.versionNumber);
							$("#isForcedUpgrade", "#form_update").val(returnObj.isForcedUpgrade);
							$("#md5", "#form_update").val(returnObj.md5);
							$("#upgradeRange", "#form_update").val(returnObj.upgradeRange);
							$("#padsnUrl", "#form_update").val(returnObj.padsnUrl);
							$("#isPush", "#form_update").val(returnObj.isPush);
							$("#url", "#form_update").val(returnObj.url);
							$("#size", "#form_update").val(returnObj.size);
							$("#description", "#form_update").val(returnObj.description);
						});
				$("#b_save").hide();
				$('#myModal_updateClientUpgradeInfo').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};

/**
 * 取消发布
 */
clientUpgradeInfoJS.softDel = function(id) {
	bootbox.confirm("确定取消?", function(result) {
		if (result) {
			var paramData = {
				id : id,
				isPublish : 1
			};
			$.ajax({
				url : '/clientUpgrade/isPublish',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/clientUpgrade/list", requestData);
					} else {
						bootbox.alert("出现异常:" + data.errorMsg);
					}
				}
			});
		}

	});
};
/**
 * 发布
 */
clientUpgradeInfoJS.reUse = function(id) {
	bootbox.confirm("确定发布?", function(result) {
		if (result) {
			var paramData = {
				id : id,
				isPublish : 2
			};
			$.ajax({
				url : '/clientUpgrade/isPublish',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/clientUpgrade/list", requestData);
					} else {
						bootbox.alert("出现异常:" + data.errorMsg);
					}
				}
			});
		}

	});
};

clientUpgradeInfoJS.updateChange = function(form, os) {
	if (os == 'android') {
		$("#appType", "#" + form).empty();
		$("#appType", "#" + form).append('<option value="pad">pad</option>');
		$("#appType", "#" + form).append('<option value="app">app</option>');

		$("#appName", "#" + form).empty();
		$("#appName", "#" + form).append('<option value="Launcher">冰箱APP</option>');
		$("#appName", "#" + form).append('<option value="mApp">手机App</option>');
		$("#appName", "#" + form).append('<option value="FM">FM APP</option>');
		$("#appName", "#" + form).append('<option value="weixiu">维修APP</option>');

		$("#versionNumber", "#" + form).attr("readonly", "readonly");
		$("#size", "#" + form).attr("readonly", "readonly");
	} else if (os == 'ios') {
		$("#appType", "#" + form).empty();
		$("#appType", "#" + form).append('<option value="app">app</option>');

		$("#appName", "#" + form).empty();
		$("#appName", "#" + form).append('<option value="mApp">手机App</option>');

		$("#versionNumber", "#" + form).removeAttr("readonly");
		$("#size", "#" + form).removeAttr("readonly");
	}
};

clientUpgradeInfoJS.doOsChange = function(form, os) {
	if (os.val() == 'android') {
		$("#appType", "#" + form).empty();
		$("#appType", "#" + form).append('<option value="pad" selected>pad</option>');
		$("#appType", "#" + form).append('<option value="app">app</option>');

		$("#appName", "#" + form).empty();
		$("#appName", "#" + form).append('<option value="Launcher" selected>冰箱APP</option>');
		$("#appName", "#" + form).append('<option value="mApp">手机App</option>');
		$("#appName", "#" + form).append('<option value="FM">FM APP</option>');
		$("#appName", "#" + form).append('<option value="weixiu">维修APP</option>');
		

		$("#versionNumber", "#" + form).attr("readonly", "readonly");
		$("#url", "#" + form).attr("readonly", "readonly");
		$("#size", "#" + form).attr("readonly", "readonly");
		
		$("#productUuid").parents(".form-group").removeClass("dpn");
		$("[name=productUuid_2]").parents(".form-group").removeClass("dpn");
	} else if (os.val() == 'ios') {
		$("#appType", "#" + form).empty();
		$("#appType", "#" + form).append('<option value="app">app</option>');

		$("#appName", "#" + form).empty();
		$("#appName", "#" + form).append('<option value="mApp">手机App</option>');

		$("#versionNumber", "#" + form).removeAttr("readonly");
		$("#url", "#" + form).removeAttr("readonly");
		$("#size", "#" + form).removeAttr("readonly");
		
		$("#platformVerDes", "#" + form).removeAttr("readonly");
		
		$("#productUuid").parents(".form-group").addClass("dpn");
		
		$("[name=productUuid_2]").parents(".form-group").addClass("dpn");
	}
};
clientUpgradeInfoJS.doAppNameChange = function(form, os) {
	if (os.val() == 'mApp') {
		$("#platformVerDes", "#" + form).removeAttr("readonly");
	} else {
		$("#platformVerDes", "#" + form).attr("readonly", "readonly");
	}
};

clientUpgradeInfoJS.appTypeChange = function(form, os){
	if(os.val() == 'app'){
		$("#productUuid").parents(".form-group").addClass("dpn");
		$("[name=productUuid_2]").parents(".form-group").addClass("dpn");
	} else if (os.val() == 'pad'){
		$("#productUuid").parents(".form-group").removeClass("dpn");
		$("[name=productUuid_2]").parents(".form-group").removeClass("dpn");
	}
};
