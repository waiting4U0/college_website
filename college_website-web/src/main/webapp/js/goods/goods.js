/**
 * goodsJS对象;
 * 
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var goodsJS = {};

/**
 * 分页;
 * 
 * @param pageNum
 */
goodsJS.saveCheck = function(formId) {
	var name = $("#name", "#" + formId).val();
	var imgUrl = $("#imgUrl", "#" + formId).val();
	var coverUrl = $("#coverUrl", "#" + formId).val();
	var defaultExpired = $("#defaultExpired", "#" + formId).val();

	if (name == "") {
		alert("食物名称必填!");
		return false;
	}
	if (imgUrl == "") {
		alert("食物图片必填!");
		return false;
	}
	if (coverUrl == "") {
		alert("食物ICON必填!");
		return false;
	}
	if (defaultExpired == "" || Number(defaultExpired) <= 0) {
		alert("默认保质期必填，且必须大于0!");
		return false;
	}
	return true;
};
goodsJS.search = function() {
	// 获取数据值1.从tr获取 2.从服务端获取
	var requestData = {
		startIndex : 1,
		s_name : $("#s_name").val(),
		opmg : $("#opmg").val(),
		opgwl : $("#opgwl").val(),
		s_categoryId : $("#s_categoryId").val()
	};
	main.menuGoTo("/goods/list", requestData);
};
goodsJS.add = function(formId) {

	$("input[type='text']", "#" + formId).each(function() {
		$(this).val("");
	});

	$('#myModal_addGoods').modal('show');
};
goodsJS.addSave = function(formId) {
	$('#myModal_addGoods').on('hidden.bs.modal', function() {
		var requestData = {
			"startIndex" : 1
		};
		main.menuGoTo("/goods/list", requestData);
	});
	if (!goodsJS.saveCheck(formId)) {
		return;
	}
	var paramData = $("#" + formId).serialize();
	$.ajax({
		url : '/goods/save',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				bootbox.alert("保存成功", function() {
					$('#myModal_addGoods').modal('hide');
				});
			} else if (data.code == 50001) {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			} else if (data.code == 50002) {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			} else {
				bootbox.alert("保存出现异常:" + data.errorMsg);
			}
		}
	});

};

goodsJS.update = function(eventTag, key) {
	// 获取数据值1.从tr获取 2.从服务端获取
	$.ajax({
		url : '/goods/getGoods',
		type : 'GET',
		data : {
			"id" : key
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {

				var goods = jQuery.parseJSON(data.data);
				$('#myModal_updateGoods').on(
						'show.bs.modal',
						function() {
							$("#id", "#form_update").val(key);
							$("#id", "#form_update").attr("accessKey", key);
							$("#name", "#form_update").val(goods.name);
							$("#pinyin", "#form_update").val(goods.pinyin);
							$("#pinyinInitial", "#form_update").val(goods.pinyinInitial);
							$("#imgUrl", "#form_update").val(goods.imgUrl);
							$("#iconUrl", "#form_update").val(goods.iconUrl);
							$("#defaultExpired", "#form_update").val(goods.defaultExpired);
							$("#expireUnit", "#form_update").val(goods.expireUnit);
							$("#expireUnit", "#form_update").change();// 值改变后，应用了select2的就会根据change进行设置
							$("#zone", "#form_update").val(goods.zone);
							$("#zone", "#form_update").change();
							$("#categoryId", "#form_update").val(goods.categoryId);
							$("#categoryId", "#form_update").change();
							$("#alias", "#form_update").val(goods.alias);
							$("#tags", "#form_update").val(goods.tags);
							$("#temperature", "#form_update").val(goods.temperature);
							$("#jdCategoryId", "#form_update").val(goods.jdCategoryId);
						});
				$('#myModal_updateGoods').modal('show');
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};
goodsJS.updateSave = function(formId) {
	bootbox.confirm("确定修改?", function(result) {
		if (result) {
			// 确实修改了，才进行列表更新
			$('#myModal_updateGoods').on('hidden.bs.modal', function() {
				var requestData = {
					"startIndex" : $("#curPageNum").val()
				};
				main.menuGoTo("/goods/list", requestData);
			});
			if (!goodsJS.saveCheck(formId)) {
				return;
			}
			var historyId = $("#id", "#form_update").attr("accessKey");
			
			$("#historyId").val(historyId);
			
			var paramData = $("#" + formId).serialize();
			$.ajax({
				url : '/goods/update',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						bootbox.alert("保存成功", function() {
							$('#myModal_updateGoods').modal('hide');
						});

					} else {
						bootbox.alert("保存出现异常:" + data.errorMsg);
					}
				}
			});
		}
	});

};
goodsJS.del = function(id) {
	bootbox.confirm("确定删除?", function(result) {
		if (result) {
			$.ajax({
				url : '/goods/delete',
				type : 'POST',
				data : {
					"id" : id,
					"startIndex" : $("#curPageNum").val()
				},
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/goods/list", requestData);
					} else {
						bootbox.alert(data.errorMsg);
					}
				}
			});
		}

	});

};
/**
 * 软删除
 */
goodsJS.softDel = function(id) {
	bootbox.confirm("确定删除?", function(result) {
		if (result) {
			var paramData = {
				id : id,
				delTag : 1
			};
			$.ajax({
				url : '/goods/delete',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/goods/list", requestData);
					} else {
						bootbox.alert(data.errorMsg);
					}
				}
			});
		}

	});

};
/**
 * 软删除重启用
 */
goodsJS.reUse = function(id) {
	bootbox.confirm("确定启用?", function(result) {
		if (result) {
			var paramData = {
				id : id,
				delTag : 0
			};
			$.ajax({
				url : '/goods/delete',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val()
						};
						main.menuGoTo("/goods/list", requestData);
					} else {
						bootbox.alert("启用出现异常:" + data.errorMsg);
					}
				}
			});
		}

	});

};
goodsJS.getGoods = function(id) {
	$.ajax({
		url : '/goods/getGoods',
		type : 'GET',
		data : {
			"id" : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				return data.data;
			} else {
				bootbox.alert("获取数据错误!");
				return null;
			}
		}
	});
};

/** 从菜谱食品删除* */
goodsJS.delFromMenuGoods = function(id, opmg) {
	bootbox.confirm("确定从菜谱食物列表删除?", function(result) {
		if (result) {
			var paramData = {
				id : id,
				opmg : opmg,
				op : "del"
			};
			$.ajax({
				url : '/goods/mmGoods',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val(),
							"opmg" : opmg
						};
						main.menuGoTo("/goods/list", requestData);
					} else {
						bootbox.alert(data.errorMsg);
					}
				}
			});
		}

	});

};

goodsJS.addToMenuGoods = function(id, opmg) {

	var paramData = {
		id : id,
		opmg : opmg,
		op : "add"
	};
	$.ajax({
		url : '/goods/mmGoods',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				var requestData = {
					"startIndex" : $("#curPageNum").val(),
					"opmg" : opmg
				};
				main.menuGoTo("/goods/list", requestData);
			} else {
				bootbox.alert("增加出现异常:" + data.errorMsg);
			}
		}
	});

};

/** 从菜谱食品删除* */
goodsJS.delFromGoodsWhitelist = function(id, opgwl) {
	bootbox.confirm("确定从食品白名单列表删除?", function(result) {
		if (result) {
			var paramData = {
				id : id,
				opgwl : opgwl,
				op : "del"
			};
			$.ajax({
				url : '/goods/mGoodsWhitelist',
				type : 'POST',
				data : paramData,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var requestData = {
							"startIndex" : $("#curPageNum").val(),
							"opgwl" : opgwl
						};
						main.menuGoTo("/goods/list", requestData);
					} else {
						bootbox.alert(data.errorMsg);
					}
				}
			});
		}

	});

};

goodsJS.addToGoodsWhitelist = function(id, opgwl) {

	var paramData = {
		id : id,
		opgwl : opgwl,
		op : "add"
	};
	$.ajax({
		url : '/goods/mGoodsWhitelist',
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				var requestData = {
					"startIndex" : $("#curPageNum").val(),
					"opgwl" : opgwl
				};
				main.menuGoTo("/goods/list", requestData);
			} else {
				bootbox.alert("增加出现异常:" + data.errorMsg);
			}
		}
	});

};

goodsJS.uploadImg = function(formId, fileId, targetId) {
	iLoading.show();
	$.ajaxFileUpload({
		url : '/upload/uploadImage', // 用于文件上传的服务器端请求地址
		secureuri : false, // 是否需要安全协议，一般设置为false
		fileElementId : fileId, // 文件上传域的ID
		dataType : 'json', // 返回值类型 一般设置为json
		success : function(data, status) // 服务器成功响应处理函数
		{

			iLoading.close();
			$("#" + targetId, $("#" + formId)).val(data.url);

			if (typeof (data.error) != 'undefined') {
				if (data.error != 0) {
					bootbox.alert("图片上传错误:" + data.message);
				} else {
					bootbox.alert(data.message);
				}
			}
		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			iLoading.close();
			bootbox.alert("图片上传错误:" + e);
		}
	});
	return false;
};

goodsJS.pinyin = function(formId) {
	var goodsName = $("#name", $("#" + formId)).val();
	$.ajax({
		url : '/goods/yinpin',
		type : 'POST',
		data : {
			'goodsName' : goodsName
		},
		dataType : 'json',
		success : function(data) {
			$("#pinyin", $("#" + formId)).val(data.full);
			$("#pinyinInitial", $("#" + formId)).val(data.jp);
		}
	});
}