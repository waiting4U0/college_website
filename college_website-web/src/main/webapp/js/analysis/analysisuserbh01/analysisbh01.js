/**
 * analysisdevctlJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
var analysisfmfaJS = {};
var analysisfriendsopJS = {};
var analysisfriendsmsgJS = {};

var init_pageSize=100;

//获取数据值fm收藏
analysisfmfaJS.search = function() {     
	
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_uuid:$("#s_uuid").val(),
			s_source_id:$("#s_source_id").val(),
			s_source_name:$("#s_source_name").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/analysisuserbh01/listfmfa",requestData);
};
//获取数据值亲友操作
analysisfriendsopJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_uuid:$("#s_uuid").val(),
			s_user_pin:$("#s_user_pin").val(),
			s_op_type:$("#s_op_type").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/analysisuserbh01/listfriendsop",requestData);
};
//获取数据亲友评论
analysisfriendsmsgJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_uuid:$("#s_uuid").val(),
			s_feed_id:$("#s_feed_id").val(),
			s_msg_type:$("#s_msg_type").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
			};
	main.menuGoTo("/analysisuserbh01/listfriendsmsg",requestData);
};

analysisfriendsmsgJS.viewmsgPic = function(imgJson) { 
	//[{"img":"http://img30.360buyimg.com/img/jfs/t2746/174/1403711955/73900/a9c1cb12/573e8339Nf8f5ece5.jpg","positionType":0}, {"img":"http://img30.360buyimg.com/img/jfs/t2656/80/1429597444/64637/38b10cde/573e8339Nbcb3f258.jpg","positionType":1}]');
	if(imgJson!=""){
		var imgArray = JSON.parse(imgJson);
		$('#myModal_viewObject').on('show.bs.modal', function () {
			$("#img01").attr("src",imgArray[0].img);
			$("#img02").attr("src",imgArray[1].img);	

		});
		$('#myModal_viewObject').modal('show');
	}else return;
	

};


