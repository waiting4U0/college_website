/**
 * screensaverJS 对象;
 * @type {{}}
 * @author cdzhangjunhao
 * @since 2017-08-28
 */
var screensaverJS = {};


screensaverJS.saveCheck = function (formId) {
    try {
        if ($("#productUuid", "#" + formId).val() === "") {
            bootbox.alert("请选择产品型号");
            return false;
        }
        if ($("#isAllday", "#" + formId).val() === "1") {
            var startTime = $("#startTime", "#" + formId).val();
            var endTime = $("#endTime", "#" + formId).val();
            if (startTime === "") {
                bootbox.alert("开始时间不能为空");
                return false;
            }
            if (endTime === "") {
                bootbox.alert("结束时间不能为空");
                return false;
            }
            if (parseInt(startTime) < 0) {
                bootbox.alert("开始时间不能小于0");
                return false;
            } else if (parseInt(startTime) > 24) {
                bootbox.alert("开始时间不能大于24");
                return false;
            }
            if (parseInt(endTime) < 0) {
                bootbox.alert("结束时间不能小于0");
                return false;
            } else if (parseInt(endTime) > 24) {
                bootbox.alert("结束时间不能大于24");
                return false;
            }
            if (parseInt(endTime) <= (parseInt(startTime))) {
                bootbox.alert("开始时间必须小于结束时间");
                return false;
            }
        }
        var duration = $("#duration", "#" + formId).val();
        if (duration === "") {
            bootbox.alert("持续时长不能为空");
            return false;
        }
        if (parseInt(duration) < 0) {
            bootbox.alert("持续时长不能小于0");
            return false;
        } else if (parseInt(duration) > 30) {
            bootbox.alert("持续时长不能大于30");
            return false;
        }
    } catch (err) {
        bootbox.alert(err.message);
        return false;
    }
    return true;
};

screensaverJS.search = function () {
    var requestData = {
        startIndex: 1,
        s_productUuid: $("#s_productUuid").val()
    };
    main.menuGoTo("/screensaver/list", requestData);
};

screensaverJS.add = function (formId) {

    $("input[type='text']", "#" + formId).each(function () {
        $(this).val("");
    });

    $('#myModal_addScreensaver').modal('show');
};

screensaverJS.addSave = function (formId) {
    $('#myModal_addScreensaver').on('hidden.bs.modal', function () {
        var requestData = {"startIndex": $("#curPageNum").val()};
        main.menuGoTo("/screensaver/list", requestData);
    });

    if (!screensaverJS.saveCheck(formId)) {
        return;
    }
    var paramData = $("#" + formId).serialize();

    $.ajax({
        url: '/screensaver/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                bootbox.alert("保存成功", function () {
                    $('#myModal_addScreensaver').modal('hide');
                });
            } else {
                bootbox.alert("保存出现异常:" + data.errorMsg);
            }
        }
    });
};

screensaverJS.update = function (eventTag, key) {
    $.ajax({
        url: '/screensaver/get',
        type: 'GET',
        data: {"id": key},
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                var returnObj = data.data;
                $('#myModal_updateScreensaver').on('show.bs.modal', function () {
                    $("#id", "#form_update").val(key);
                    $("#productUuid", "#form_update").val(returnObj.productUuid);
                    $("#process", "#form_update").val(returnObj.process);
                    $("#isAllday", "#form_update").val(returnObj.isAllday);
                    if (returnObj.isAllday === 2) {
                        $("#startTimeUpdate").css("display", "none");
                        $("#endTimeUpdate").css("display", "none");
                        $("#startTime", "#form_update").val();
                        $("#endTime", "#form_update").val();
                    } else {
                        $("#startTimeUpdate").css("display", "block");
                        $("#endTimeUpdate").css("display", "block");
                        $("#startTime", "#form_update").val(returnObj.startTime);
                        $("#endTime", "#form_update").val(returnObj.endTime);
                    }
                    $("#duration", "#form_update").val(returnObj.duration);
                });
                $('#myModal_updateScreensaver').modal('show');
            } else {
                bootbox.alert("获取数据错误!");
                return null;
            }
        }
    });
};

screensaverJS.updateSave = function (formId) {
    bootbox.confirm("确定修改?", function (result) {
        if (result) {
            $('#myModal_updateScreensaver').on('hidden.bs.modal', function () {
                var requestData = {"startIndex": $("#curPageNum").val()};
                main.menuGoTo("/screensaver/list", requestData);
            });

            if (!screensaverJS.saveCheck(formId)) {
                return;
            }

            var paramData = $("#" + formId).serialize();
            $.ajax({
                url: '/screensaver/update',
                type: 'POST',
                data: paramData,
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        bootbox.alert("保存成功", function () {
                            $('#myModal_updateScreensaver').modal('hide');
                        });

                    } else {
                        bootbox.alert("保存出现异常:" + data.errorMsg);
                    }
                }
            });
        }
    });
};

screensaverJS.delete = function (eventTag, id) {
    bootbox.confirm("确定删除?", function (result) {
        if (result) {
            $.ajax({
                url: '/screensaver/delete/' + id,
                type: 'DELETE',
                data: {"id": id, "startIndex": $("#curPageNum").val()},
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        bootbox.alert("删除成功");
                        var requestData = {"startIndex": $("#curPageNum").val()};
                        main.menuGoTo("/screensaver/list", requestData);
                    } else {
                        bootbox.alert(data.errorMsg);
                    }
                }
            });
        }
    });

};
