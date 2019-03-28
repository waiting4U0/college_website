/**
 * customMessageJS 对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-08-24
 */
var customMessageJS = {};

customMessageJS.search = function () {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData = {
        startIndex: 1,
        s_title: $("#s_title").val(),
        s_typeCode: $("#s_typeCode").val(),
        s_startDate: $("#s_startDate").val(),
        s_endDate: $("#s_endDate").val()
    };
    main.menuGoTo("/customMessage/list", requestData);
};

customMessageJS.add = function (formId) {

    $("input[type='text']", "#" + formId).each(function () {
        $(this).val("");
    });

    $('#myModal_addCustomMessage').modal('show');
};

customMessageJS.addSave = function (formId) {
    if (!customMessageJS.saveCheck(formId)) {
        return;
    }

    $('#myModal_addCustomMessage').on('hidden.bs.modal', function () {
        var requestData = {"startIndex": $("#curPageNum").val()};
        main.menuGoTo("/customMessage/list", requestData);
    });

    var paramData = $("#" + formId).serialize();

    $.ajax({
        url: '/customMessage/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                bootbox.alert("保存成功", function () {
                    $('#myModal_addCustomMessage').modal('hide');
                });
            } else {
                bootbox.alert("保存出现异常:" + data.errorMsg);
            }
        }
    });
};

customMessageJS.update = function (eventTag, key) {
    var paramData = "id=" + key;
    $.ajax({
        url: '/customMessage/get',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                var returnObj = data.data;
                $('#myModal_updateCustomMessage').on('show.bs.modal', function () {
                        $("#id", "#form_update").val(key);
                        $("#messageTypeCode", "#form_update").val(returnObj.messageTypeCode);
                        $("#messageTitle", "#form_update").val(returnObj.message_title);
                        $("#displayType", "#form_update").val(returnObj.display_type);
                        $("#displayImageUrl", "#form_update").val(returnObj.display_image_url);
                        $("#targetType", "#form_update").val(returnObj.targetType);
                        if (returnObj.targetType === 2) {
                            $("#productUuid", "#form_update").val(returnObj.productUuid);
                            $("#productModelUpdate").css("display", "block");
                        } else if (returnObj.targetType === 3) {
                            $("#padSnUrl", "#form_update").val(returnObj.padSnUrl);
                            $("#padSnUrlUpdate").css("display", "block");
                        }
                        $("#clickType", "#form_update").val(returnObj.click_type);
                        if (returnObj.click_type === 1) {
                            $("#linkUrl", "#form_update").val(returnObj.link_url);
                            $("#linkUrlUpdate").css("display", "block");
                        } else if (returnObj.click_type === 2) {
                            $("#nativePage", "#form_update").val(returnObj.native_page);
                            $("#nativePageUpdate").css("display", "block");
                        }
                        $("#sendTimeType", "#form_update").val(returnObj.sendTimeType);
                        if (returnObj.sendTimeType === 2) {
                            $("#sendTime", "#form_update").val(returnObj.sendTime.substring(0, 16).replace(" ", "T"));
                            $("#scheduledUpdate").css("display", "block");
                        }
                        $("#isBrightenScreen", "#form_update").val(returnObj.isBrightenScreen);
                        $("#activityEndTime", "#form_update").val(returnObj.activity_end_time.substring(0, 16).replace(" ", "T"));
                        $("#timeToLive", "#form_update").val(returnObj.timeToLive / 3600);
                        $("#originalPageParam", "#form_update").val(returnObj.originalPageParam);
                    }
                );
                $('#myModal_updateCustomMessage').modal('show');
            }
            else {
                bootbox.alert("获取数据错误!");
                return null;
            }
        }
    });
};

customMessageJS.updateSave = function (formId) {

    if (!customMessageJS.saveCheck(formId)) {
        return;
    }

    bootbox.confirm("确定修改?", function (result) {
        if (result) {
            $('#myModal_updateCustomMessage').on('hidden.bs.modal', function () {
                var requestData = {"startIndex": $("#curPageNum").val()};
                main.menuGoTo("/customMessage/list", requestData);
            });

            var paramData = $("#" + formId).serialize();
            $.ajax({
                url: '/customMessage/update',
                type: 'POST',
                data: paramData,
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        bootbox.alert("保存成功", function () {
                            $('#myModal_updateCustomMessage').modal('hide');
                        });
                    } else {
                        bootbox.alert("保存出现异常:" + data.errorMsg);
                    }
                }
            });
        }
    });
};

customMessageJS.uploadImg = function (formId, fileId, targetId) {
    iLoading.show();
    $.ajaxFileUpload(
        {
            url: '/upload/uploadImage', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: fileId, //文件上传域的ID另外后台action中的参数
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
                iLoading.close();
                $("#" + targetId, $("#" + formId)).val(data.url);
                if (typeof (data.error) !== 'undefined') {
                    if (data.error !== 0) {
                        bootbox.alert("图片上传错误:" + data.message);
                    } else {
                        bootbox.alert(data.message);
                    }
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                iLoading.close();
                bootbox.alert("图片上传错误:" + e);
            }
        }
    );
    return false;
};

customMessageJS.uploadTxt = function (formId, fileId, targetId) {
    iLoading.show();
    $.ajaxFileUpload(
        {
            url: '/upload/uploadText',
            secureuri: false,
            fileElementId: fileId,
            dataType: 'json',
            success: function (data, status) {
                iLoading.close();
                $("#" + targetId, $("#" + formId)).val(data.url);
                if (typeof (data.error) !== 'undefined') {
                    if (data.error !== 0) {
                        bootbox.alert("文件上传错误:" + data.message);
                    } else {
                        bootbox.alert(data.message);
                    }
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                iLoading.close();
                bootbox.alert("文件上传错误:" + e);
            }
        }
    );
    return false;
};

customMessageJS.view = function (eventTag, key) {
    var paramData = "id=" + key;
    //alert(paramData);
    $.ajax({
        url: '/customMessage/get',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                var object = data.data;
                $('#myModal_viewObject').on('show.bs.modal', function () {
                    $("#messageType", "#form_view").html(object.messageType);
                    $("#messageTitle", "#form_view").html(object.message_title);

                    if (object.display_type === 1) {
                        $("#displayType", "#form_view").html("小图");
                    } else {
                        $("#displayType", "#form_view").html("大图");
                    }
                    $("#displayImageUrl", "#form_view").html(object.display_image_url);

                    if (object.targetType === 1) {
                        $("#targetType", "#form_view").html("全部用户");
                        $("#brandModelView").css("display", "none");
                        $("#brandModelLabelView").css("display", "none");
                        $("#padSnView").css("display", "none");
                        $("#padSnLabelView").css("display", "none");
                    } else if (object.targetType === 2) {
                        $("#targetType", "#form_view").html("产品型号");
                        $("#brandModelView", "#form_view").html(object.brandModel);
                        $("#brandModelView").css("display", "block");
                        $("#brandModelLabelView").css("display", "block");
                        $("#padSnView").css("display", "none");
                        $("#padSnLabelView").css("display", "none");
                    } else if (object.targetType === 3) {
                        $("#targetType", "#form_view").html("设备序列号");
                        $("#padSnView", "#form_view").html(object.padSnUrl);
                        $("#brandModelView").css("display", "none");
                        $("#brandModelLabelView").css("display", "none");
                        $("#padSnView").css("display", "block");
                        $("#padSnLabelView").css("display", "block");
                    }

                    if (object.click_type === 1) {
                        $("#clickType", "#form_view").html("链接地址");
                        $("#linkUrlView", "#form_view").html(object.link_url);
                        $("#linkUrlView").css("display", "block");
                        $("#linkUrlLabelView").css("display", "block");
                        $("#nativePageView").css("display", "none");
                        $("#nativePageLabelView").css("display", "none");
                    } else {
                        $("#clickType", "#form_view").html("原生页面ID");
                        $("#nativePageView", "#form_view").html(object.native_page);
                        $("#linkUrlView").css("display", "none");
                        $("#linkUrlLabelView").css("display", "none");
                        $("#nativePageView").css("display", "block");
                        $("#nativePageLabelView").css("display", "block");
                    }

                    if (object.sendTimeType === 1) {
                        $("#sendTimeType", "#form_view").html("立即");
                        $("#sendTimeView").css("display", "none");
                        $("#sendTimeLabelView").css("display", "none");
                    } else {
                        $("#sendTimeType", "#form_view").html("定时");
                        $("#sendTimeView", "#form_view").html(object.sendTime);
                        $("#sendTimeView").css("display", "block");
                        $("#sendTimeLabelView").css("display", "block");
                    }

                    if (object.isBrightenScreen === 1) {
                        $("#isBrightenScreen", "#form_view").html("否");
                    } else {
                        $("#isBrightenScreen", "#form_view").html("是");
                    }

                    $("#activityEndTime", "#form_view").html(object.activity_end_time);

                    $("#timeToLive", "#form_view").html(object.timeToLive / 3600 + "小时");
                    if(object.originalPageParam != null){
                        $("#originalPageParam", "#form_view").html(object.originalPageParam);
                    }else {
                        $("#originalPageParam", "#form_view").html("");
                    }

                });
                $('#myModal_viewObject').modal('show');

            } else {
                bootbox.alert("获取数据错误!");
                return null;
            }
        }
    });
};

customMessageJS.stop = function (id) {

};


customMessageJS.saveCheck = function (formId) {
    try {
        if ($("#messageTypeCode", "#" + formId).val() === null) {
            bootbox.alert("请选择消息分类");
            return false;
        }
        if ($("#messageTitle", "#" + formId).val() === "") {
            bootbox.alert("请输入标题");
            return false;
        } else if ($("#messageTitle", "#" + formId).val().length > 30) {
            bootbox.alert("标题长度不能超过30个字符");
            return false;
        }
        if ($("#displayImageUrl", "#" + formId).val() === "") {
            bootbox.alert("请上传图片");
            return false;
        }
        if ($("#targetType", "#" + formId).val() === "2") {
            if ($("#productUuid", "#" + formId).val() === null) {
                bootbox.alert("请选择产品型号");
                return false;
            }
        } else if ($("#targetType", "#" + formId).val() === "3") {
            if ($("#padSnUrl", "#" + formId).val() === "") {
                bootbox.alert("请上传设备序列号文件");
                return false;
            }
        }
        if ($("#clickType", "#" + formId).val() === "1") {
            if ($("#linkUrl", "#" + formId).val() === "") {
                bootbox.alert("请输入链接地址");
                return false;
            }
        } else if ($("#clickType", "#" + formId).val() === "2") {
            if ($("#nativePage", "#" + formId).val() === "") {
                bootbox.alert("请输入原生页面ID");
                return false;
            }
        }
        if ($("#sendTimeType", "#" + formId).val() === "2") {
            if ($("#sendTime", "#" + formId).val() === "") {
                bootbox.alert("请输入发送时间");
                return false;
            }
        }
        if ($("#activityEndTime", "#" + formId).val() === "") {
            bootbox.alert("请输入活动结束时间");
            return false;
        }
        if ($("#timeToLive", "#" + formId).val() === "") {
            bootbox.alert("请输入离线保存时间");
            return false;
        } else if (parseInt($("#timeToLive", "#" + formId).val()) < 1) {
            bootbox.alert("离线保存时间不能小于1");
            return false;
        } else if (parseInt($("#timeToLive", "#" + formId).val()) > 24) {
            bootbox.alert("离线保存时间不能大于24");
            return false;
        }

    } catch (err) {
        bootbox.alert(err.message);
        return false;
    }


    return true;
};