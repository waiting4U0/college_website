/**
 * analysisdevJS对象
 *  @type {{}}
 *  @author weijunlong@jd.com
 *  @since 2017-08-02
 */
var analysisdevJS = {};
var init_pageSize=100;
/**
 * 分页;
 * @param pageNum
 */
analysisdevJS.saveCheck = function(formId)
{

    return true;
};
analysisdevJS.search = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	var requestData={
			startIndex:1,
			pageSize:init_pageSize,
			s_pad_sn:$("#s_pad_sn").val(),
			s_device_sn:$("#s_device_sn").val(),
			s_uuid:$("#s_uuid").val(),
			s_city:$("#s_city").val(),
			s_user_pin:$("#s_user_pin").val(),
			s_binded:$("#s_binded").val(),
			is_test:$("#is_test").val(),
        	induction_state:$("#induction_state").val(),
			
			first_activate_start_date:$("#first_activate_start_date").val(),
			first_activate_end_date:$("#first_activate_end_date").val(),
			first_bind_start_date:$("#first_bind_start_date").val(),
			first_bind_end_date:$("#first_bind_end_date").val(),

			last_activate_start_date:$("#last_activate_start_date").val(),
			last_activate_end_date:$("#last_activate_end_date").val(),
			last_bind_start_date:$("#last_bind_start_date").val(),
			last_bind_end_date:$("#last_bind_end_date").val()
			};
	main.menuGoTo("/analysisdevctl/listdev",requestData);
};

analysisdevJS.viewdev = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取
	$.ajax({
	    url: '/analysisdevctl/viewdev',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {
	                    var object=data.data;
						$('#myModal_viewObject').on('show.bs.modal', function () {
							$("#padSn","#form_view").html(object.padSn);
							$("#deviceSn","#form_view").html(object.deviceSn);	
							$("#productUuid","#form_view").html(object.productUuid);
							$("#feedId","#form_view").html(object.feedId);	
							$("#bindPin","#form_view").html(object.bindPin);
							$("#deviceIp","#form_view").html(object.deviceIp);	
							$("#city","#form_view").html(object.city);
							$("#activateDate","#form_view").html(object.activateDateFormat);	
							
							$("#isOnline","#form_view").html(object.online);
							if(object.binded==1)
							   $("#isBinded","#form_view").html('是');
							else $("#isBinded","#form_view").html('否');
							;
							$("#bindDate","#form_view").html(object.bindDateFormat);
							$("#unBindDate","#form_view").html(object.unBindDateFormat);	

							if(object.inductionState != null){
								if(object.inductionState == 1){
                                    $("#inductionStateOpen","#form_view").html("已开启");
                                    if(object.inductionDistance != null){
                                    	if (0 == object.inductionDistance){
                                            $("#inductionDistance","#form_view").html("近");
										}else if(1 == object.inductionDistance) {
                                            $("#inductionDistance","#form_view").html("标准");
                                        }else {
                                            $("#inductionDistance","#form_view").html("");
                                        }
									}
                                    $("#platformVersionOpen","#form_view").html(object.platformVersion);
                                    $("#appVersionOpen","#form_view").html(object.appVersion);
                                    $("#fmVersionOpen","#form_view").html(object.fmVersion);

                                    $("#inductionNull").hide();
                                    $("#inductionClose").hide();
                                    $("#inductionOpen").show();
								}else {
                                    $("#inductionStateClose","#form_view").html("已关闭");
                                    $("#inductionDistance","#form_view").html("");
                                    $("#platformVersionClose","#form_view").html(object.platformVersion);
                                    $("#appVersionClose","#form_view").html(object.appVersion);
                                    $("#fmVersionClose","#form_view").html(object.fmVersion);

                                    $("#inductionOpen").hide();
                                    $("#inductionNull").hide();
                                    $("#inductionClose").show();
								}
							}
							else {
                                $("#inductionStateNull","#form_view").html("");
                                $("#inductionDistance","#form_view").html("");
                                $("#platformVersionNull","#form_view").html(object.platformVersion);
                                $("#appVersionNull","#form_view").html(object.appVersion);
                                $("#fmVersionNull","#form_view").html(object.fmVersion);

                                $("#inductionOpen").hide();
                                $("#inductionClose").hide();
                                $("#inductionNull").show();
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

analysisdevJS.viewdevpic = function(eventTag,key) {     
	//获取数据值1.从tr获取  2.从服务端获取

	$.ajax({
	    url: '/analysisdevctl/viewdevpic',
	    type: 'GET',
	    data: {"id":key},
	    dataType: 'json',
	    success: function (data) {	            	
					if (data.code == 200) {

	                    var object=jQuery.parseJSON(data.data);
						$('#myModal_viewPic').on('show.bs.modal', function () {
							$("#id","#form_view").val(key);
							$("#title","#form_view").val(object.title);				
						});
						$('#myModal_viewPic').modal('show');
						
					} else {
						bootbox.alert("获取数据错误!");
						return null;
					}
	    }
	});
};


analysisdevJS.searchdevpic = function() {     
	//获取数据值1.从tr获取  2.从服务端获取
	window.location="/analysisdevctl/listdevpic?feedId="+$("#feedId").val()+"&startIndex=1&pageSize=10&s_start_date="+encodeURI(encodeURI($("#s_start_date").val()))+"&s_end_date="+encodeURI(encodeURI($("#s_end_date").val()));

};
