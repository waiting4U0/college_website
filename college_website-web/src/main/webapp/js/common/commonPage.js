/**
 * commonPageJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
 
var commonPageJS = {};

/**
 * 分页;
 * @param pageNum
 */
commonPageJS.turnPageCommon =  function(url,pageNum,pageSize)
{
	 if(!isNaN(pageNum))
	 {
		 commonPageJS.getPageList(url,pageNum * 1,pageSize*1);
	 }
};
commonPageJS.getPageList = function(_url,_page,_pageSize) 
{
    var jsonData =  {
    		startIndex : _page,
    		pageSize: _pageSize
    };
    main.menuGoTo(_url,jsonData);  
};

commonPageJS.hidePic =function(e){
    $('#div_show').css("display","none");
 };
commonPageJS.showPic =function(e,picUrl){
    x=e.clientX;
    y=e.clientY;
	$('#img_show').attr("src",picUrl);
	$('#div_show').css("left",x+"px");
	$('#div_show').css("top",y+"px");
	$('#div_show').css("display","block");
};
commonPageJS.showPicAlignLeft =function(e,picUrl){
    x=e.clientX-300;
    y=e.clientY;
	$('#img_show').attr("src",picUrl);
	$('#div_show').css("left",x+"px");
	$('#div_show').css("top",y+"px");
	$('#div_show').css("display","block");
};