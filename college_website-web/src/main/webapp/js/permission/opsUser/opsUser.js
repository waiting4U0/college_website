var opsUserJS = {};

/**
 * 分页;
 * @param pageNum
 */
opsUserJS.saveCheck = function (formId) {
    var opsUserErp = $("#opsUserErp", "#" + formId).val();
    var opsUserName = $("#opsUserName", "#" + formId).val();
    if (opsUserErp == "") {
        alert("ERP必填!");
        return false;
    }
    if (opsUserName == "") {
        alert("NAME必填!");
        return false;
    }
    return true;

};

opsUserJS.add = function (formId) {
    $("input[type='text']", "#" + formId).each(function () {
        $(this).val("");
    });
    $.ajax({
        url: '/opsUser/getRoleList',
        type: 'GET',
        //data: {"id":key},
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                var returnObj = data.data;
                $('#myModal_addOpsUser').on('show.bs.modal', function () {
                    var element = $("#check_group", "#form_add");
                    element.empty();
                    for (var i = 0; i < returnObj.length; i++) {
                        element.append('<label class="checkbox-inline">' +
                            '<input id="check_' + returnObj[i].id +
                            '" name="opsResource" onchange="opsUserJS.chooseOne(this,' + returnObj[i].id + ')" type="checkbox" value="' + returnObj[i].id + '"> '
                            + returnObj[i].name + '</label>');
                    }
                });
                $('#myModal_addOpsUser').modal('show');
            } else {
                bootbox.alert("获取数据错误!");
                return null;
            }

        }
    });

    $('#myModal_addOpsUser').modal('show');
};

opsUserJS.chooseOne = function (eventTag, id) {
    if ($(eventTag).prop("checked")) {
        $("#check_" + id, ".checkbox-inline").prop("checked", true);
    } else {
        $("#check_" + id, ".checkbox-inline").prop("checked", false);
    }
};


opsUserJS.addSave = function (formId) {
    $('#myModal_addOpsUser').on('hidden.bs.modal', function () {
        var requestData = {"startIndex": $("#curPageNum").val()};
        main.menuGoTo("/opsUser/list", requestData);
    });
    if (!opsUserJS.saveCheck("form_add")) return;

    var paramData = $("#" + formId).serialize();
    $.ajax({
        url: '/opsUser/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                bootbox.alert("保存成功", function () {
                    $('#myModal_addOpsUser').modal('hide');
                });
            } else {
                bootbox.alert("保存出现异常:" + data.errorMsg);
            }
        }
    });


};
opsUserJS.update = function (eventTag, key) {
    //获取数据值1.从tr获取  2.从服务端获取
    var trId = "tr_" + key;
    var name = $("td[name='ops_user_name']", $("#" + trId)).text();
    var erp = $("td[name='ops_user_erp']", $("#" + trId)).text();
    var role = $("td[name='ops_user_role']", $("#" + trId)).text();

    $("#opsUserErp", "#form_update").val(erp);
    $("#opsUserName", "#form_update").val(name);
    $("#opsUserId", "#form_update").val(key);

    $.ajax({
        url: '/opsUser/getRoleList' + erp,
        type: 'GET',
        //data: {"id":key},
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                var returnObj = data.data;
                $('#myModal_updateOpsUser').on('show.bs.modal', function () {
                    var element = $("#check_group", "#form_update");
                    element.empty();
                    for (var i = 0; i < returnObj.length; i++) {
                        var isChecked = returnObj[i].checked == "1" ? "checked" : "";
                        element.append('<label class="checkbox-inline"> <input id="check_' + returnObj[i].id + '" onchange="opsRoleJS.chooseOne(this,' + returnObj[i].id + ')" name="opsResource" ' + isChecked + ' type="checkbox" value="' + returnObj[i].id + '"> ' + returnObj[i].name + '</label>');
                    }
                });
                $('#myModal_updateOpsUser').modal('show');
            } else {
                bootbox.alert("获取数据错误!");
                return null;
            }
        }
    });
};
opsUserJS.updateSave = function (formId) {
    if (!opsUserJS.saveCheck("form_update")) return;

    bootbox.confirm("确定修改?", function (result) {
        if (result) {
            //确实修改了，才进行列表更新
            $('#myModal_updateOpsUser').on('hidden.bs.modal', function () {
                var requestData = {"startIndex": $("#curPageNum").val()};
                main.menuGoTo("/opsUser/list", requestData);
            });

            var paramData = $("#" + formId).serialize();
            $.ajax({
                url: '/opsUser/save',
                type: 'POST',
                data: paramData,
                dataType: 'json',
                success: function (data) {
                    if (data.code == 200) {
                        bootbox.alert("保存成功", function () {
                            $('#myModal_updateOpsUser').modal('hide');
                        });

                    } else {
                        bootbox.alert("保存出现异常:" + data.errorMsg);
                    }
                }
            });
        }
    });

};
opsUserJS.del = function (id) {
    bootbox.confirm("确定删除?", function (result) {
        if (result) {
            $.ajax({
                url: '/opsUser/' + id,
                type: 'DELETE',
                data: {"categoryId": id, "startIndex": $("#curPageNum").val()},
                dataType: 'json',
                success: function (data) {
                    if (data.code == 200) {
                        var requestData = {"startIndex": $("#curPageNum").val()};
                        main.menuGoTo("/opsUser/list", requestData);
                    } else {
                        bootbox.alert("删除出现异常，请重新删除!");
                    }
                }
            });
        }

    });

};


