/**
 * listPageJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
 
var listPageJS = {};
var init_pageSize=100;
/**
 * 分页;
 * @param pageNum
 */
listPageJS.turnPage =  function(pageNum)
{
	if(!isNaN(pageNum))
	 {
		listPageJS.getPageList("/analysisdevctl/listdev",pageNum * 1,init_pageSize);
	 }
};
listPageJS.turnPageCommon =  function(url,pageNum,pageSize)
{
	 if(!isNaN(pageNum))
	 {
		  listPageJS.getPageList(url,pageNum * 1,pageSize*1);
	 }
};
listPageJS.getPageList = function(_url,_page,_pageSize) 
{
	var jsonData =  {
    		startIndex : _page,
    		pageSize : _pageSize,
			s_pad_sn:$("#s_pad_sn").val(),
			s_device_sn:$("#s_device_sn").val(),
			s_uuid:$("#s_uuid").val(),
			s_city:$("#s_city").val(),
			s_user_pin:$("#s_user_pin").val(),
			s_binded:$("#s_binded").val(),
			
			s_start_active_date:$("#s_start_active_date").val(),
			s_end_active_date:$("#s_end_active_date").val(),
			s_start_bind_date:$("#s_start_bind_date").val(),
			s_end_bind_date:$("#s_end_bind_date").val()
    };
    main.menuGoTo(_url,jsonData);  
};

/**
 * 分页;
 * @param pageNum
 */
var listdevpicPageJS={};
listdevpicPageJS.turnPage =  function(pageNum)
{
	if(!isNaN(pageNum))
	 {
		listdevpicPageJS.getPageList("/analysisdevctl/listdevpic",pageNum * 1,10);
	 }
};
listdevpicPageJS.turnPageCommon =  function(url,pageNum,pageSize)
{
	 if(!isNaN(pageNum))
	 {
		 listdevpicPageJS.getPageList(url,pageNum * 1,pageSize*1);
	 }
};
listdevpicPageJS.getPageList = function(_url,_page,_pageSize) 
{
	var s_start_date=$("#s_start_date").val();
	var s_end_date=$("#s_end_date").val();
	var feedId=$("#feedId").val();
	window.location=_url+"?"+"feedId="+feedId+"&startIndex="+_page+"&pageSize="+_pageSize+"&s_start_date="+encodeURI(encodeURI(s_start_date))+"&s_end_date="+encodeURI(encodeURI(s_end_date));
};