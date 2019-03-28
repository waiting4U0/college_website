/**
 * listPageJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
 */
 
var listPageJS = {};
var init_pageSize=100;
var request_url = $("#request_url").val();
/**
 * 分页;
 * @param pageNum
 */
listPageJS.turnPage =  function(pageNum)
{
	if(!isNaN(pageNum))
	 {
		listPageJS.getPageList(request_url,pageNum * 1,init_pageSize);
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
			s_uuid:$("#s_uuid").val(),
			s_city:$("#s_city").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val(),
			source_page_num:$("#source_page_num").val(),
			s_food_name:$("#s_food_name").val(),
			s_date_time:$("#s_date_time").val()
    };
    main.menuGoTo(_url,jsonData);  
};