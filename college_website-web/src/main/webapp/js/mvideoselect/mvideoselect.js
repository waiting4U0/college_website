/**
 * mvideoselectJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var mvideoselectJS = {};

/**
 * 分页;
 * @param pageNum
 */
mvideoselectJS.saveCheck = function(formId)
{
    return true;
};
mvideoselectJS.search = function() {     
	window.location="/menuVideo/selectlist?"+"startIndex=1"+"&s_name="+encodeURI(encodeURI($("#s_name").val()));
};
mvideoselectJS.getObject  = function(id) {  
		$.ajax({
		    url: '/menuVideo/getObject',
		    type: 'GET',
		    data: {"id":id},
		    dataType: 'json',
		    success: function (data) {	            	
						if (data.code == 200) {
                            return data.data;
						} else {
							bootbox.alert("获取数据错误!");
							return null;
						}
		    }
		});
};
