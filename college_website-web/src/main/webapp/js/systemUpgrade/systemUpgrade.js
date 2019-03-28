var systemUpgradeJS = {};

/**
 * 分页;
 * 
 * @param pageNum
 */
systemUpgradeJS.saveCheck = function(formId) {
	var url =$("#url","#"+formId).val();
	var size = $("#size","#"+formId).val();
	var padsnUrl = $("#padsnUrl","#"+formId).val();
	var description = $("#description","#"+formId).val();
	var productUuid = $("#productUuid","#"+formId).val();
	var versionNumber = $("#versionNumber","#"+formId).val();
	var oldVersionNumber = $("#oldVersionNumber","#"+formId).val();
	var upgradeRange = $("#upgradeRange","#"+formId).val();
	var upgradeStrategy = $("#upgradeStrategy","#"+formId).val();
	
	url = $.trim(url);
	size = $.trim(size);
	padsnUrl = $.trim(padsnUrl);
	description = $.trim(description);
	productUuid = $.trim(productUuid);
	versionNumber = $.trim(versionNumber);
	oldVersionNumber = $.trim(oldVersionNumber);
	
	if(!url || url == ""){
		alert("包的下载地址必填!");
		return false;
	}
	
	if(!size || size == ""){
		alert("包的大小必填!");
		return false;
	}
	
	var regExp = new RegExp("txt$"); 
	if(padsnUrl != "" && !regExp.test(padsnUrl)){
		alert("设备序列号只能上传txt格式的文件!");
		return false;
	}
	
	if(!description || description == ""){
		alert("最新版本变化必填!");
		return false;
	}
	
	if(!productUuid || productUuid == ""){
		alert("UUID必填!");
		return false;
	}
	
	var reg = new RegExp('^[0-9]+\.+[0-9]+\.[0-9]+$');
	if(!versionNumber || versionNumber == ""){
		alert("新版本号必填!");
		return false;
	}
	
	if(!reg.test(versionNumber)){
		alert("新版本号格式：XX.XX.XX，XX是数字，位数不用限制！");
		return false;
	}
	
	if(upgradeStrategy == 2){
		if(!oldVersionNumber || oldVersionNumber == ""){
			alert("包类型为差分包，老版本号必填!");
			return false;
		}
		if(!reg.test(oldVersionNumber)){
			alert("老版本号格式：XX.XX.XX，XX是数字，位数不用限制！");
			return false;
		}
	}
	if(upgradeRange == 2){
		if(!padsnUrl || padsnUrl == ""){
			alert("部分升级，设备序列号url不能为空");
			return false;
		}
	}
	
	
	return true;
};

systemUpgradeJS.search = function() {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData={
        startIndex:1,
        s_productUuid:$("#s_productUuid").val(),
        s_newVersionNumber:$("#s_newVersionNumber").val()
    };
    main.menuGoTo("/systemUpgrade/list",requestData);
};

systemUpgradeJS.add = function(formId) {
/*
	$("input[type='text']", "#" + formId).each(function() {
		$(this).val("");
	});
*/
	$('#myModal_addSystemUpgrade').modal('show');
};

systemUpgradeJS.addSave = function(formId) {
	$('#myModal_addSystemUpgrade').on('hidden.bs.modal', function() {
		var requestData = {
			"startIndex" : 1
		};
		main.menuGoTo("/systemUpgrade/list", requestData);
	});
	if (!systemUpgradeJS.saveCheck(formId)) {
		return;
	}
	var paramData = $("#" + formId).serialize();
	$.ajax({
		url: '/systemUpgrade/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                bootbox.alert("保存成功", function() {
                    $('#myModal_addSystemUpgrade').modal('hide');
                });
            } else {
                bootbox.alert("保存出现异常:"+data.errorMsg);
            }
        }
	});
};

systemUpgradeJS.update = function(eventTag,key) {
    //获取数据值1.从tr获取  2.从服务端获取
    $.ajax({
        url: '/systemUpgrade/getSystemUpgradeById',
        type: 'GET',
        data: {"id":key},
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {

                var returnObj=jQuery.parseJSON(data.data);
                $('#myModal_updateSystemUpgrade').on('show.bs.modal', function () {

                	 $("#id","#form_update").val(key);
                     $("#productUuid","#form_update").val(returnObj.productUuid);
                     $("#upgradeStrategy","#form_update").val(returnObj.upgradeStrategy);
                     $("#oldVersionNumber","#form_update").val(returnObj.oldVersionNumber);
                     $("#versionNumber","#form_update").val(returnObj.versionNumber);
                     $("#isForcedUpgrade","#form_update").val(returnObj.isForcedUpgrade);
                     $("#isReminder","#form_update").val(returnObj.isReminder);
                     $("#isKeyVersion","#form_update").val(returnObj.isKeyVersion);
                     $("#md5","#form_update").val(returnObj.md5);
                     $("#upgradeRange","#form_update").val(returnObj.upgradeRange);
                     $("#upgradeRange","#form_update").change();
                     $("#padsnUrl","#form_update").val(returnObj.padsnUrl);
                     $("#isPush","#form_update").val(returnObj.isPush);
                     $("#url","#form_update").val(returnObj.url);
                     $("#size","#form_update").val(returnObj.size);
                     $("#description","#form_update").val(returnObj.description);
                     
                });
                $("#b_save").show();
                $('#myModal_updateSystemUpgrade').modal('show');
            } else {
                bootbox.alert("获取数据错误!");
                return null;
            }
        }
    });
};

systemUpgradeJS.updateSave = function(formId) {
    bootbox.confirm("确定修改?", function(result) {
        if(result){
            //确实修改了，才进行列表更新
            $('#myModal_updateSystemUpgrade').on('hidden.bs.modal', function () {
                var requestData={"startIndex":$("#curPageNum").val()};
                main.menuGoTo("/systemUpgrade/list", requestData);
            });
            if(!systemUpgradeJS.saveCheck(formId)){
                return;
            }

            var paramData=$("#"+formId).serialize();
            $.ajax({
                url: '/systemUpgrade/save',
                type: 'POST',
                data: paramData,
                dataType: 'json',
                success: function (data) {
                    if (data.code == 200) {
                        bootbox.alert("保存成功", function() {
                            $('#myModal_updateSystemUpgrade').modal('hide');
                        });

                    } else {
                        bootbox.alert("保存出现异常:"+data.errorMsg);
                    }
                }
            });
        }
    });
};


systemUpgradeJS.del  = function(id) {
    bootbox.confirm("确定删除?", function(result) {
        if(result){
            $.ajax({
                url: '/systemUpgrade/'+ id,
                type: 'DELETE',
                dataType: 'json',
                success: function (data) {
                    if (data.code == 200) {
                        var requestData={"startIndex":$("#curPageNum").val()};
                        main.menuGoTo("/systemUpgrade/list", requestData);
                    } else {
                        bootbox.alert("删除出现异常，请重新删除!");
                    }
                }
            });
        }
    });
};



systemUpgradeJS.ajaxFileUpload = function(id){
    $("#uploading", "#form_add").show();
    $("#uploading", "#form_update").show();
    $.ajaxFileUpload({
        url:'/systemUpgrade/upLoadFile',            //需要链接到服务器地址
        secureuri:false,
        fileElementId:id,                       //文件选择框的id属性
        dataType: 'json',                       //服务器返回的格式，可以是json, xml
        success: function (data)       //相当于java中try语句块的用法
        {
            if (data.code == 200) {
                var url = data.data.path;
                url = url.replace("amp;", "");
                url = url.replace("amp;", "");
                url = url.replace("amp;", "");
                url = url.replace("amp;", "");
                url = url.replace("amp;", "");
                var download_url = url.replace("360buyimg","jd");
                //$("#url").val(download_url);
                $("#size","#form_add").val(data.data.size);
                $("#size","#form_update").val(data.data.size);
                $("#url","#form_add").val(download_url);
                $("#url","#form_update").val(download_url);
                $("#md5","#form_add").val(data.data.md5);
                $("#md5","#form_update").val(data.data.md5);
                //$("#fileName").val(data.data.name);
                $("#uploading", "#form_add").hide();
                $("#uploading", "#form_update").hide();
            } else {
                bootbox.alert("上传失败");
                $("#uploading", "#form_add").hide();
                $("#uploading", "#form_update").hide();
            }
        },
        error: function ()            //相当于java中catch语句块的用法
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

systemUpgradeJS.ajaxPadSnUpload = function(id){
    $("#uploadingPadSn", "#form_add").show();
    $("#uploadingPadSn", "#form_update").show();
    $.ajaxFileUpload({
        url:'/systemUpgrade/upLoadPadSn',            //需要链接到服务器地址
        secureuri:false,
        fileElementId:id,                       //文件选择框的id属性
        dataType: 'json',                       //服务器返回的格式，可以是json, xml
        success: function (data)       //相当于java中try语句块的用法
        {
            if (data.code == 200) {
                var url = data.data.path;
                url = url.replace("amp;", "");
                url = url.replace("amp;", "");
                url = url.replace("amp;", "");
                url = url.replace("amp;", "");
                url = url.replace("amp;", "");
                var download_url = url.replace("360buyimg","jd");
                $("#padsnUrl","#form_add").val(download_url);
                $("#padsnUrl","#form_update").val(download_url);
                $("#uploadingPadSn", "#form_add").hide();
                $("#uploadingPadSn", "#form_update").hide();
            } else {
                bootbox.alert("上传失败");
                $("#uploadingPadSn", "#form_add").hide();
                $("#uploadingPadSn", "#form_update").hide();
            }
        },
        error: function ()            //相当于java中catch语句块的用法
        {
            bootbox.alert("上传出现错误");
            $("#uploadingPadSn", "#form_add").hide();
            $("#uploadingPadSn", "#form_update").hide();
        }
    });
};




/**
 * 取消发布
 */
systemUpgradeJS.softDel  = function(id) { 
	   bootbox.confirm("确定取消?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						isPublish:1
				};
			    $.ajax({
			        url: '/systemUpgrade/isPublish',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/systemUpgrade/list",requestData);
						} else {
							bootbox.alert("出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
};
/**
 * 发布
 */
systemUpgradeJS.reUse  = function(id) { 
	   bootbox.confirm("确定发布?", function(result) {
		   if(result){		    
				var paramData={
						id:id,
						isPublish:2
				};
			    $.ajax({
			        url: '/systemUpgrade/isPublish',
			        type: 'POST',
			        data: paramData,
			        dataType: 'json',
			        success: function (data) {	            	
						if (data.code == 200) {				
							var requestData={"startIndex":$("#curPageNum").val()};
							main.menuGoTo("/systemUpgrade/list",requestData);
						} else {
							bootbox.alert("出现异常:"+data.errorMsg);
						}
			        }
			    });
		   }
	      
	   });
};


systemUpgradeJS.doOldVersionNumberChange = function(form, os) {
	if (os.val() == '1') {
		$("#oldVersionNumber", "#" + form).attr("readonly", "readonly");
	} else {
		$("#oldVersionNumber", "#" + form).removeAttr("readonly");
	}
};
