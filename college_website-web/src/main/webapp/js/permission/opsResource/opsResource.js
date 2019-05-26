var opsResourceJS = {};

/**
 * 分页;
 * @param pageNum
 */
opsResourceJS.saveCheck = function (formId) {
    var opsResourceName = $("#opsResourceName", "#" + formId).val();
    var opsResourceUrl = $("#opsResourceUrl", "#" + formId).val();
    var opsResouceKey = $("#opsResouceKey", "#" + formId).val();
    var opsResouceIcon = $("#opsResouceIcon", "#" + formId).val();
    var opsResourceParentId = $("#opsResourceParentId", "#" + formId).val();
    if (opsResourceParentId != "") {
        if (opsResouceKey == "" || opsResourceUrl == "") {
            alert("子菜单key和url参数必填");
            return false;
        }
    }
    if (opsResourceName == "" || opsResouceIcon == "") {
        alert("参数必填");
        return false;
    }
    return true;

};

opsResourceJS.add = function (formId) {
    $("input[type='text']", "#" + formId).each(function () {
        $(this).val("");
    });
    $.ajax({
        url: '/opsResource/getParentList',
        type: 'GET',
        //data: {"id":key},
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                var returnObj = data.data;
                $('#myModal_addOpsResource').on('show.bs.modal', function () {
                    var element = $("#opsResourceParentId", "#form_add");
                    element.empty();
                    element.append('<option value="">无</option>');
                    $.each(returnObj, function (key, value) {
                        element.append('<option value="' + key + '">' + value + '</option>');
                    });

                });
                $('#myModal_addOpsResource').modal('show');
            } else {
                bootbox.alert("获取数据错误!");
                return null;
            }
        }
    });

    $('#myModal_addOpsResource').modal('show');
};
opsResourceJS.addSave = function (formId) {
    $('#myModal_addOpsResource').on('hidden.bs.modal', function () {
        var requestData = {"startIndex": $("#curPageNum").val()};

        main.menuGoTo("/opsResource/list", requestData);
    });
    if (!opsResourceJS.saveCheck("form_add")) return;

    var paramData = $("#" + formId).serialize();
    $.ajax({
        url: '/opsResource/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                bootbox.alert("保存成功", function () {
                    $('#myModal_addOpsResource').modal('hide');
                });
            } else {
                bootbox.alert("保存出现异常:" + data.errorMsg);
            }
        }
    });


};
opsResourceJS.update = function (eventTag, key) {
    //获取数据值1.从tr获取  2.从服务端获取
    var trId = "tr_" + key;
    var resourceId = key;
    var fName = $("td[name='ops_father_name']", $("#" + trId)).text();
    var cName = $("td[name='ops_child_name']", $("#" + trId)).text();
    var resourceName = fName == "" ? cName : fName;
    var resourceUrl = $("td[name='ops_resource_url']", $("#" + trId)).text();
    var resourceKey = $("td[name='ops_resource_key']", $("#" + trId)).text();
    var parentId = $("#" + trId).attr("parent_id");
    var resourceIcon = $("td[name='ops_resource_icon']", $("#" + trId)).text();

    $("#opsResourceId", "#form_update").val(key);
    $("#opsResourceName", "#form_update").val(resourceName);
    $("#opsResourceUrl", "#form_update").val(resourceUrl);
    $("#opsResouceKey", "#form_update").val(resourceKey);
    $("#opsResouceIcon", "#form_update").val(resourceIcon);
    $.ajax({
        url: '/opsResource/getParentList',
        type: 'GET',
        //data: {"id":key},
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                var returnObj = data.data;
                $('#myModal_updateOpsResource').on('show.bs.modal', function () {
                    var element = $("#opsResourceParentId", "#form_update");
                    element.empty();
                    element.append('<option value="">无</option>');
                    $.each(returnObj, function (key, value) {
                        if (key == resourceId) {
                            return;
                        }
                        if (key == parentId) {
                            element.append('<option value="' + key + '" selected>' + value + '</option>');
                        } else {
                            element.append('<option value="' + key + '">' + value + '</option>');
                        }

                    });
                });
                $('#myModal_updateOpsResource').modal('show');
            } else {
                bootbox.alert("获取数据错误!");
                return null;
            }
        }
    });
};
opsResourceJS.updateSave = function (formId) {
    if (!opsResourceJS.saveCheck("form_update")) return;

    bootbox.confirm("确定修改?", function (result) {
        if (result) {
            //确实修改了，才进行列表更新
            $('#myModal_updateOpsResource').on('hidden.bs.modal', function () {
                var requestData = {"startIndex": $("#curPageNum").val()};
                main.menuGoTo("/opsResource/list", requestData);
            });
            var paramData = $("#" + formId).serialize();
            $.ajax({
                url: '/opsResource/save',
                type: 'POST',
                data: paramData,
                dataType: 'json',
                success: function (data) {
                    if (data.code == 200) {
                        bootbox.alert("保存成功", function () {
                            $('#myModal_updateOpsResource').modal('hide');
                        });

                    } else {
                        bootbox.alert(data.errorMsg);
                    }
                }
            });
        }
    });

};
opsResourceJS.del = function (id) {
    bootbox.confirm("确定删除?", function (result) {
        if (result) {
            $.ajax({
                url: '/opsResource/' + id,
                type: 'DELETE',
                data: {"categoryId": id, "startIndex": $("#curPageNum").val()},
                dataType: 'json',
                success: function (data) {
                    if (data.code == 200) {
                        var requestData = {"startIndex": $("#curPageNum").val()};
                        main.menuGoTo("/opsResource/list", requestData);
                    } else {
                        bootbox.alert(data.errorMsg);
                    }
                }
            });
        }

    });

};


