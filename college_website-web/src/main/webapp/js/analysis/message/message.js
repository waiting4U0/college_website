/**
 * Created by cdshenjian on 2016/11/4.
 */
var messageJS = {};
var init_pageSize = 100;

messageJS.search = function() {
    //获取数据值1.从tr获取  2.从服务端获取
    var requestData={
        startIndex:1,
        pageSize:init_pageSize,
        s_uuid:$("#txt_uuid").val(),
        s_pad_sn:$("#txt_pad_sn").val(),
        src:$("#src").val(),
        operate:$("#operate").val(),
        s_start_date:$("#s_start_date").val(),
        s_end_date:$("#s_end_date").val()
    };
    main.menuGoTo("/messageboard/list",requestData);
};

messageJS.exportAsExcel = function() {
    $("#exportForm").submit();
};

messageJS.hidePic =function(){
    $('#div_show').css("display","none");
};

messageJS.showPic =function(e,picUrl){
    x=e.clientX;
    y=e.clientY;
    $('#img_show').attr("src",picUrl);
    $('#div_show').css("left",x+"px");
    $('#div_show').css("top",y+"px");
    $('#div_show').css("display","block");
};