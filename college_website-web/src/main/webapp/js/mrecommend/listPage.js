/**
 * listPageJS对象;
 * @type {{}}
 * @author fengsonghao
 * @since 2016-01-05
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
		listPageJS.getPageList("/menuRecommend/list",pageNum * 1,6);
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
			s_name:$("#s_name").val()
    };
    main.menuGoTo(_url,jsonData);  
};

var listSelectPageJS = {};

/**
 * 分页;
 * @param pageNum
 */
listSelectPageJS.turnPage =  function(pageNum)
{
	if(!isNaN(pageNum))
	 {
		listSelectPageJS.getPageList("/menuRecommend/listselect",pageNum * 1,6);
	 }
};
listSelectPageJS.turnPageCommon =  function(url,pageNum,pageSize)
{
	 if(!isNaN(pageNum))
	 {
		 listSelectPageJS.getPageList(url,pageNum * 1,pageSize*1);
	 }
};
listSelectPageJS.getPageList = function(_url,_page,_pageSize) 
{
    window.location=_url+"?"+"startIndex="+_page+"&pageSize="+_pageSize+"&s_name="+encodeURI(encodeURI($("#s_name").val()));
};