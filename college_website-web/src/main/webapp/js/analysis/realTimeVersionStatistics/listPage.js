/**
 * listPageJS对象;
 * @type {{}}
 * @author weijunlong@jd.com
 * @since 2016-11-21
 */
 
var listPageJS = {};
var init_pageSize = 100;
/**
 * 分页;
 * @param pageNum
 */
listPageJS.turnPage =  function(pageNum)
{
	if(!isNaN(pageNum))
	 {
		listPageJS.getPageList("/realTimeVersionStatistics/list",pageNum * 1,init_pageSize);
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
    		s_uuid : $("#s_uuid").val(),
    		s_appType : $("#s_appType").val()
    };
    main.menuGoTo(_url,jsonData);  
};