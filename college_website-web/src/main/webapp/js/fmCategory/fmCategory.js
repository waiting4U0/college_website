/**
 * fmCategoryJS 对象;
 * @type {{}}
 * @author cdwangkun1
 * @since 2017-10-31
 */
var fmCategoryJS = {};

fmCategoryJS.search = function () {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData = {
        startIndex: 1
    };
    main.menuGoTo("/fmCategory/list", requestData);
};

fmCategoryJS.add = function (formId) {

    $("input[type='text']", "#" + formId).each(function () {
        $(this).val("");
    });
    $("#categoryType", "#" + formId).val("0");
    $("#radioType", "#" + formId).val("0");
    $("#radioDiv").css("display", "block");
    $("#categoryIdDiv").css("display", "none");
    $("#categoryNameDiv").css("display", "none");

    $('#myModal_addFMCategory').modal('show');
};

fmCategoryJS.addSave = function (formId) {
    if (!fmCategoryJS.saveCheck(formId)) {
        return;
    }

    $('#myModal_addFMCategory').on('hidden.bs.modal', function () {
        var requestData = {"startIndex": $("#curPageNum").val()};
        main.menuGoTo("/fmCategory/list", requestData);
    });

    var paramData = $("#" + formId).serialize();

    $.ajax({
        url: '/fmCategory/save',
        type: 'POST',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                bootbox.alert("保存成功", function () {
                    $('#myModal_addFMCategory').modal('hide');
                });
            } else {
                bootbox.alert("保存出现异常:" + data.errorMsg);
            }
        }
    });
};

fmCategoryJS.del  = function(id) {
    bootbox.confirm("确定删除?", function(result) {
        if(result){
            $.ajax({
                url: '/fmCategory/' + id,
                type: 'DELETE',
                dataType: 'json',
                success: function (data) {
                    if (data.code == 200) {
                        var requestData={"startIndex":$("#curPageNum").val()};
                        main.menuGoTo("/fmCategory/list",requestData);
                    } else {
                        bootbox.alert("删除出现异常，请重新删除!");
                    }
                }
            });
        }
    });

};
fmCategoryJS.saveCheck = function (formId) {
    try {
        if ($("#sortId", "#" + formId).val() === "") {
            bootbox.alert("请输入排序号");
            return false;
        } else if (isNaN($("#sortId", "#" + formId).val())) {
            bootbox.alert("排序号只能为整数");
            return false;
        }
        if ($("#categoryType", "#" + formId).val() === 1){
            if ($("#categoryId", "#" + formId).val() === "") {
                bootbox.alert("请输入分类ID");
                return false;
            } else if (isNaN($("#categoryId", "#" + formId).val())) {
                bootbox.alert("分类ID只能为整数");
                return false;
            }
            if ($("#categoryName", "#" + formId).val() === "") {
                bootbox.alert("请输入分类名");
                return false;
            } else if ($("#categoryName", "#" + formId).val().length > 20) {
                bootbox.alert("分类名长度不能超过20个字符");
                return false;
            }
        }


    } catch (err) {
        bootbox.alert(err.message);
        return false;
    }


    return true;
};