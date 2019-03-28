/**
 * insidePictureJS对象;
 * @type {{}}
 * @author lishuangquan@jd.com
 * @since 2017-03-08
 */
var insidePictureJS = {};
var init_pageSize=100;

insidePictureJS.search = function() {
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
		startIndex:1,
		pageSize:init_pageSize,
		s_pad_sn:$("#s_pad_sn").val(),
		s_device_sn:$("#s_device_sn").val(),
		s_uuid:$("#s_uuid").val(),
		s_city:$("#s_city").val(),
		s_start_upload_date:$("#s_start_upload_date").val(),
		s_end_upload_date:$("#s_end_upload_date").val()
	};
	main.menuGoTo("/insidePicture/listInsidePictures",requestData);
};

insidePictureJS.viewPic = function(padSn) {
	//获取数据值1.从tr获取  2.从服务端获取
	$("#dialog_pic_sn").val(padSn);

	insidePictureJS.correctClick();

	$('#myModal_viewObject').modal('show');
};

insidePictureJS.originalClick = function () {
	//e.preventDefault();
	//$(this).tab('show');
	var padSn = $("#dialog_pic_sn").val();
	$("#originalPic_box").attr("src", $("#tr_"+padSn+" div[name='box']").find("img").attr("original_pic"));
	$("#originalPic_door").attr("src", $("#tr_"+padSn+" div[name='door']").find("img").attr("original_pic"));

	$('#myTabs').find('a[href="#originalPic"]').tab('show');
};

insidePictureJS.correctClick = function () {
	//e.preventDefault();
	//$(this).tab('show');
	$(".recognition_div").remove();
	Rect.init("correctPic_div_box", "result_json_box");
	Rect.init("correctPic_div_door", "result_json_door");

	var padSn = $("#dialog_pic_sn").val();
	$("#correctPic_box").attr("src", $("#tr_"+padSn+" div[name='box']").find("img").attr("correct_pic"));
	$("#correctPic_door").attr("src", $("#tr_"+padSn+" div[name='door']").find("img").attr("correct_pic"));

	$("#feed_id_label").text($("#tr_"+padSn).attr("feed_id_tr"));
	$("#pad_sn_label").text($("#tr_"+padSn+" td[name='padSn']").text());
	$("#upload_time_label").text($("#tr_"+padSn+" td[name='uploadTime']").text());

	$('#myTabs').find('a[href="#correctPic"]').tab('show');
};

insidePictureJS.prePic = function () {
	var padSnDialog = $("#dialog_pic_sn");
	var padSn = padSnDialog.val();
	var padSnTr = $("#tr_" + padSn);

	if(padSnTr.next().length > 0) {
		$("#button_next").attr("disabled", false);
	}
	if(padSnTr.prev().length <= 0) {
		$("#button_pre").attr("disabled", "disabled");
		return;
	}
	padSnDialog.val(padSnTr.prev().attr("name"));
	insidePictureJS.correctClick();
};

insidePictureJS.nextPic = function () {
	var padSnDialog = $("#dialog_pic_sn");
	var padSn = padSnDialog.val();
	var padSnTr = $("#tr_" + padSn);

	if(padSnTr.prev().length > 0) {
		$("#button_pre").attr("disabled", false);
	}
	if(padSnTr.next().length <= 0) {
		$("#button_next").attr("disabled", "disabled");
		return;
	}
    padSnDialog.val(padSnTr.next().attr("name"));
	insidePictureJS.correctClick();


	//$('#myTabs').find('a[href="#correctPic"]').tab('show');
};

insidePictureJS.showRecognitionDiv = function (input) {
	var $recognitionDiv = $(".recognition_div");
    if($recognitionDiv.length <= 0) {
		Rect.init("correctPic_div_box", "result_json_box");
		Rect.init("correctPic_div_door", "result_json_door");
		$(input).text("隐藏识别框");
	}else{
		$recognitionDiv.remove();
		$(input).text("显示识别框");
	}
};
insidePictureJS.showCnName = function (input) {
	var $recognitionDivNode = $(".recognition_div a");
	for (var i = 0; i < $recognitionDivNode.length; i++){
		$($recognitionDivNode[i]).text($($recognitionDivNode[i]).attr("name_cn"));
	}
};
insidePictureJS.showEnName = function (input) {
	var $recognitionDivNode = $(".recognition_div a");
	for (var i = 0; i < $recognitionDivNode.length; i++){
		$($recognitionDivNode[i]).text($($recognitionDivNode[i]).attr("name_en"));
	}
};
insidePictureJS.showIdName = function (input) {
	var $recognitionDivNode = $(".recognition_div a");
	for (var i = 0; i < $recognitionDivNode.length; i++){
		$($recognitionDivNode[i]).text($($recognitionDivNode[i]).attr("name_id"));
	}
};


var Rect = {
	obj: null,
	container: null,
	init: function(containerId, resultJsonInput) {
		//Rect.container = $("#" + containerId);
		Rect.container = document.getElementById(containerId);

		if(Rect.container) {

			Rect.start(resultJsonInput);
		}else{
			alert('You should specify a valid container!');
		}
	},
	start: function(resultJsonInput) {
		var padSnDialog = document.getElementById("dialog_pic_sn");
		var padSn = padSnDialog.value;
		var padSnTr = document.getElementById("tr_" + padSn);
		var jsonStr = padSnTr.getAttribute(resultJsonInput);
		var jsonObj = eval ("(" + jsonStr + ")");

		var goodsArray = jsonObj.goods;
		for (var i=0;i<goodsArray.length;i++)
		{
			var positionArray = goodsArray[i].position;
			for (var j=0;j<positionArray.length;j++)
			{
				var o = Rect.obj = document.createElement('div');
				o.style.position = "absolute";
				o.style.zIndex="5555";

				o.style.left = positionArray[j].xt/2;
				o.style.top = positionArray[j].yt/2;
				o.style.height = (positionArray[j].yb-positionArray[j].yt)/2;
				o.style.width = (positionArray[j].xb-positionArray[j].xt)/2;

				o.style.border = "solid red 1px";
				o.className = "recognition_div";

				var deleteLink = document.createElement('a');
				deleteLink.href="#";
				deleteLink.style.color = "red";

				deleteLink.innerText = goodsArray[i].goods_id;
				deleteLink.setAttribute("name_id", goodsArray[i].goods_id);
				deleteLink.setAttribute("name_cn", goodsArray[i].goods_name);
				deleteLink.setAttribute("name_en", goodsArray[i].goods_en_name);

				o.appendChild(deleteLink);
				Rect.container.appendChild(o);
			}
		}
	}
};