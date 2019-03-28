/**
 * listPageJS对象;
 * @type {{}}
 * @author weijunlong@jd.com
 * @since 2016-11-21
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
		listPageJS.getPageList("/analysisactivateHistory/list",pageNum * 1,init_pageSize);
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
			startIndex:_page,
			pageSize:init_pageSize,
			s_pad_sn:$("#s_pad_sn").val(),
			s_uuid:$("#s_uuid").val(),
			s_start_date:$("#s_start_date").val(),
			s_end_date:$("#s_end_date").val()
    };
    main.menuGoTo(_url,jsonData);  
};