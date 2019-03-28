/**
 * goodsJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var goodsExtJS = {};

/**
 * 分页;
 * @param pageNum
 */
goodsExtJS.saveCheck = function(formId)
{
	var nutrition=$("#nutrition","#"+formId).val();
    if (nutrition!=''&&!nutrition.match("^\{(.+:.+,*){1,}\}$")){
    	bootbox.alert("【营养参数】输入错误!营养参数必须为json格式.");
    	return false;
    }
    return true;
};

function onUpdate(idx) {
	alert(idx);
	alert($("#glucose"+idx).val());
}

goodsExtJS.updateSave = function(idx) {
	bootbox.confirm("确定修改?", function(result) {
		if(result){
			var data = {};
			data.id = $("#id"+idx).val();
			data.glucose = $("#glucose"+idx).val();
			data.calories = $("#calories"+idx).val();
			data.protein = $("#protein"+idx).val();
			data.fat = $("#fat"+idx).val();
			data.carbohydrate = $("#carbohydrate"+idx).val();
			data.df = $("#df"+idx).val();
			data.quadrant = $("#quadrant"+idx).val();
			data.scoreLab = $("#score_lab"+idx).val();
            data.booheeCode = $("#boohee_code"+idx).val();

			$.ajax({
				url: '/goodsExt/save',
				type: 'POST',
				data: data,
				dataType: 'json',
				success: function (data) {
					if (data.code == 200) {
						bootbox.alert("保存成功", function() {
							main.menuGoTo("/goodsExt/list",null);
						});
					} else {
						bootbox.alert("保存出现异常:"+data.errorMsg);
					}
				}
			});
		}
	});
};

goodsExtJS.search = function() {
	var requestData={
		startIndex:1,
		s_name:$("#s_name").val(),
		s_categoryId:$("#s_categoryId").val()
	};
	main.menuGoTo("/goodsExt/list",requestData);
};
