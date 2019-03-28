/**
 * customMessageTypeJS 对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-08-22
 */
var customMessageTypeJS = {};

customMessageTypeJS.saveCheck = function (formId) {
    try {
        if ($("#typeName", "#" + formId).val() === "") {
            bootbox.alert("请输入消息分类名称");
            return false;
        } else if ($("#typeName", "#" + formId).val().length > 12) {
            bootbox.alert("消息分类名称不能超过12个字符");
            return false;
        }
        if ($("#typeCode", "#" + formId).val() === "") {
            bootbox.alert("请输入消息分类code");
            return false;
        } else if ($("#typeName", "#" + formId).val().length > 12) {
            bootbox.alert("消息分类code不能超过12个字符");
            return false;
        }
        if ($("#typeIcon", "#" + formId).val() === "") {
            bootbox.alert("请上传消息分类icon");
            return false;
        }
        if ($("#typeIconWhite", "#" + formId).val() === "") {
            bootbox.alert("请上传消息分类icon白");
            return false;
        }
    } catch (err) {
        bootbox.alert(err.message);
        return false;
    }


    return true;
};

customMessageTypeJS.search = function () {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData = {
        startIndex: 1,
        s_uuid: $("#s_uuid").val()
    };
    main.menuGoTo("/customMessageType/list", requestData);
};

customMessageTypeJS.add = function (formId) {

    $("input[type='text']", "#" + formId).each(function () {
        $(this).val("");
    });

    $('#myModal_addCustomMessageType').modal('show');
};

customMessageTypeJS.addSave = function (formId) {
    $('#myModal_addCustomMessageType').on('hidden.bs.modal', function () {
        var requestData = {"startIndex": $('#curPageNum').val()};
        main.menuGoTo("/customMessageType/list", requestData);
    });
    if (!customMessageTypeJS.saveCheck(formId)) {
        return;
    }
    var paramData = $("#" + formId).serialize();
    $.ajax({
        url: '/customMessageType/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                bootbox.alert("保存成功", function () {
                    $('#myModal_addCustomMessageType').modal('hide');
                });
            } else {
                bootbox.alert("保存出现异常:" + data.errorMsg);
            }
        }
    });
};

customMessageTypeJS.update = function (eventTag, key) {
    //获取数据值1.从tr获取  2.从服务端获取
    $.ajax({
        url: '/customMessageType/get',
        type: 'GET',
        data: {"id": key},
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                var returnObj = jQuery.parseJSON(data.data);
                $('#myModal_updateCustomMessageType').on('show.bs.modal', function () {
                    $("#id", "#form_update").val(returnObj.id);
                    $("#typeName", "#form_update").val(returnObj.typeName);
                    $("#typeCode", "#form_update").val(returnObj.typeCode);
                    $("#typeIcon", "#form_update").val(returnObj.typeIcon);
                    $("#typeIconWhite", "#form_update").val(returnObj.typeIconWhite);
                    $("#isActive", "#form_update").val(returnObj.isActive);
                });
                $('#myModal_updateCustomMessageType').modal('show');
            } else {
                bootbox.alert("获取数据错误!");
                return null;
            }
        }
    });
};

customMessageTypeJS.updateSave = function (formId) {
    bootbox.confirm("确定修改?", function (result) {
        if (result) {
            $('#myModal_updateCustomMessageType').on('hidden.bs.modal', function () {
                var requestData = {"startIndex": $("#curPageNum").val()};
                main.menuGoTo("/customMessageType/list", requestData);
            });
            if (!customMessageTypeJS.saveCheck(formId)) {
                return;
            }

            var paramData = $("#" + formId).serialize();
            $.ajax({
                url: '/customMessageType/update',
                type: 'POST',
                data: paramData,
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        bootbox.alert("保存成功", function () {
                            $('#myModal_updateCustomMessageType').modal('hide');
                        });
                    } else {
                        bootbox.alert("保存出现异常:" + data.errorMsg);
                    }
                }
            });
        }
    });
};

customMessageTypeJS.setActive = function (id) {
    bootbox.confirm("确定修改?", function (result) {
        if (result) {
            var paramData = "id=" + id + "&isActive=2";
            $.ajax({
                url: '/customMessageType/update',
                type: 'POST',
                data: paramData,
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        var requestData = {"startIndex": $("#curPageNum").val()};
                        main.menuGoTo("/customMessageType/list", requestData);
                    } else {
                        bootbox.alert("修改失败:" + data.errorMsg);
                    }
                }
            });
        }
    });
};

customMessageTypeJS.setInactive = function (id) {
    bootbox.confirm("确定修改?", function (result) {
        if (result) {
            var paramData = "id=" + id + "&isActive=1";
            $.ajax({
                url: '/customMessageType/update',
                type: 'POST',
                data: paramData,
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        var requestData = {"startIndex": $("#curPageNum").val()};
                        main.menuGoTo("/customMessageType/list", requestData);
                    } else {
                        bootbox.alert("修改失败:" + data.errorMsg);
                    }
                }
            });
        }
    });
};

customMessageTypeJS.uploadImg = function (formId, fileId, targetId) {
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