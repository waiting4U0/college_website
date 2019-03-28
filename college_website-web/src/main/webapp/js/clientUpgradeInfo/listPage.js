/**
 * listPageJS对象;
 * @type {{}}
 * @author weijunlong@jd.com
 * @since 2016-07-21
 */
var listPageJS = {};

/**
 * 分页;
 * @param pageNum
 */
listPageJS.turnPage =  function(pageNum)
{
	if(!isNaN(pageNum))
	 {
		listPageJS.getPageList("/clientUpgrade/list",pageNum * 1,5);
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
			s_feedId:$("#s_appName").val(),
			s_productUuid:$("#s_productUuid").val()
    };
    main.menuGoTo(_url,jsonData);  
};